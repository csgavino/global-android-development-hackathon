class ServiceController < ApplicationController
  
  
  def category_index
    categories = Category.all
    response = []
    i = 0
    categories.each do |category|
      events = Event.select("image_url").where(:category_id => category.id)
      event = events.last
      cat = { :id => category.id, :name => category.name, :event => event}
      #cat['id'] =>  category.id
      response[i] = cat
      i = i + 1
    end

    render :json  =>  response
  end
  
  
  def category_show
    category = Category.find_by_id(params[:id])
    events = Event.select("image_url").where(:category_id => category.id)
    event = events.last
    
    response = { :id => category.id, :name => category.name, :event => event}
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
  
  
  def events_show
      event = Event.find_by_id(params[:id])
      render  :json => event
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
