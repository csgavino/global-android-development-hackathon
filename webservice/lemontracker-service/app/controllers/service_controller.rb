class ServiceController < ApplicationController
  
  #USER_ID 499999be-7d59-4a0b-a8c4-879a0dfc0f46
  #APP_ID 09c5b5b6-581f-4e49-b91c-a82aa43f26d5
  
  #remote: http://124.106.85.9:18888/services/soap
  
  def category_index
    categories = Category.all
    response = []
    i = 0
    
    date_today = DateTime.now.in_time_zone('Asia/Taipei')
    event = Event.find(:first ,:order => "date_start ASC", 
        :conditions => 
        ["date_start <= ? AND date_end >= ?", date_today, date_today]
        )
        cat = { :id => 0, :name => "Today", :image_url => event.image_url}
    response[i] = cat
    i = i + 1
    
    categories.each do |category|
      cat = nil
      event = Event.find_by_category_id(category.id, :order => "date_start DESC")
    
      cat = { :id => category.id, :name => category.name}
      
      if event != nil
        if category.id == 5
          cat = { :id => category.id, :name => category.name, :image_url => event.image_url}
        else
          cat = { :id => category.id, :name => category.name, :image_url => event.thumb_url}
        end
      end
      
      response[i] = cat
      i = i + 1
      
    end
    render :json  =>  response
  end
  
  
  def category_show
    category = Category.find_by_id(params[:id])
    event = Event.find_by_category_id(category.id, :order => "date_start ASC")
    response = { :id => category.id, :name => category.name}
    if event != nil
      response = { :id => category.id, :name => category.name, :image_url => event.thumb_url}
    end
    render :json  => response
  end
  
  
  def category_events
    if params[:id] == "0"
      date_today = DateTime.now.in_time_zone('Asia/Taipei')
      events = Event.find(:all,:order => "date_start ASC", 
          :conditions => 
          ["date_start <= ? AND date_end >= ?", date_today, date_today]
          )
    else
      events = Event.find_all_by_category_id(params[:id], :order => "date_start ASC")
    end
    render :json  => events
  end
  
  def category_today
    date_today = DateTime.now.in_time_zone('Asia/Taipei')
    
    events = Event.find(:all,:order => "date_start ASC", 
        :conditions => 
        ["date_start <= ? AND date_end >= ?", date_today, date_today]
        )
        
    render :json  => events
  end
  
  def events_index
      events = Event.all
      render :json  => events
  end
  
  def events_show
      event = Event.find_by_id(params[:id])
      render  :json => event
  end
  
  def events_search
    #data = ActiveSupport::JSON.decode(request.body.string)
    param = params[:searchstring]
    
    events = Event.find(:all, :conditions => ['name like ?',  "%"+param[0] +"%"])
    render  :json => events 
    
  end

  def events_search_location
    events = Event.all
    response = []
    i = 0
    events.each do |event|
      if in_circle(params[:longitude][0], params[:latitude][0], 0.0899104604, event.longitude, event.latitude)
        response[i] = event
        i = i + 1
      end  
    end
    render  :json => response 
  end
  
  def radius_test
    render  :json => in_circle(0, 0, 50, 36, 36)
  end
  
  
  def soap_register   
      response = MyWebService.invoke :get_consent, {:u_name => 'pfombgm68', :u_pin => '21737318', :m_s_i_s_d_n => '09178950998', :api_type => 'location'}
      render  :json => response
  end
  
  #SOAP
  
  def soap_locate
    response = MyWebService.invoke :get_loc, {:u_name => 'pfombgm68', :u_pin => '21737318', :m_s_i_s_d_n => '09178950998'}
    render  :json => response
  end
  
  def soap_save_location    
   
    message = params[:message]
    param = message[:param]
    param_transaction_id = param[1]
    param_X = param[3]
    param_Y = param[4]
    
    loc = Location.new
    loc.longitude = param_Y[:value]
    loc.latitude = param_X[:value]
    loc.transaction_id = param_transaction_id[:value]
    
    if loc.save 
      puts "SAVED LOCATION"
    end
    
  end
  
  def locations_show
    location = Location.find_by_transaction_id(params[:transaction_id]) 
    
    longitude_string = location.longitude.split(".")
    
    seconds = longitude_string[0][-2,3]
    minutes = longitude_string[0][-4,2]
    degrees = longitude_string[0][-7,3]
    if degrees == nil
      degrees = longitude_string[0][-6,2]
    end
    
    milli = "."+longitude_string[1][-4,3]
    header = longitude_string[1][-1,1]
    
    #puts degrees+minutes+seconds+milli+header
    
    total_seconds = (minutes.to_f*60) + seconds.to_f + milli.to_f
    
    center_x = degrees.to_f + (total_seconds/3600)
    
    if header == 'W'
      center_x = center_x * -1
    end
     
    
    latitude_string = location.latitude.split(".")
    
    seconds = latitude_string[0][-2,3]
    minutes = latitude_string[0][-4,2]
    degrees = latitude_string[0][-7,3]
    if degrees == nil
      degrees = latitude_string[0][-6,2]
    end
    milli = "."+latitude_string[1][-4,3]
    header = latitude_string[1][-1,1]
    
    #puts degrees+minutes+seconds+milli+header
    
    total_seconds = (minutes.to_f*60) + seconds.to_f + milli.to_f
    
    center_y = degrees.to_f + (total_seconds/3600)
    if header == 'S'
      center_y = center_y * -1
    end
    
    puts degrees
    puts "center_x : " + center_x.to_s
    puts "center_y : " + center_y.to_s
    
    events = Event.all
    response = []
    i = 0
    events.each do |event|
      if in_circle(center_x, center_y, 0.0899104604, event.longitude, event.latitude)
        response[i] = event
        i = i + 1
      end  
    end
    render  :json => response
  end
  
  private

      def in_circle(center_x, center_y, radius, x, y)
          square_dist = (center_x - x) ** 2 + (center_y - y) ** 2
          return square_dist <= radius ** 2
      end
  
end
