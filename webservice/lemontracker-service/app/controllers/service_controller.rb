class ServiceController < ApplicationController
  
  
  def category_index
    categories = Category.all
    response = []
    i = 0
    
    date_today = DateTime.now.in_time_zone('Asia/Taipei')
    event = Event.find(:first, 
        :conditions => 
        ["date_start <= ? AND date_end >= ?", date_today, date_today]
        )
        cat = { :id => 0, :name => "Today", :image_url => event.image_url}
    response[i] = cat
    i = i + 1
    
    categories.each do |category|
      cat = nil
      event = Event.find_by_category_id(category.id, :order => "date_start ASC")
    
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
    events = Event.find_all_by_category_id(params[:id], :order => "date_start ASC")
    render :json  => events
  end
  
  def category_today
    date_today = DateTime.now.in_time_zone('Asia/Taipei')
    
    events = Event.find(:all, 
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
    data = ActiveSupport::JSON.decode(request.body.string)
    events = Event.find(:all, :conditions => ['name like ?',  "%"+data['searchstring'] +"%"])
    render  :json => events 
    
  end

  def events_search_location
    data = ActiveSupport::JSON.decode(request.body.string)
    events = Event.all
    response = []
    i = 0
    events.each do |event|
      if in_circle(data['longitude'], data['latitude'], data['radius'], event.longitude, event.latitude)
        response[i] = event
        i = i + 1
      end  
    end
    render  :json => response 
  end
  
  def radius_test
    render  :json => in_circle(0, 0, 50, 36, 36)
  end
  
  private

      def in_circle(center_x, center_y, radius, x, y)
          square_dist = (center_x - x) ** 2 + (center_y - y) ** 2
          return square_dist <= radius ** 2
      end
  
end
