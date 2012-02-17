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
    events = Event.find_all_by_category_id(params[:id], :order => "date_start DESC")
    render :json  => events
  end
  
  def events_show
      event = Event.find_by_id(params[:id])
      render  :json => event
  end
  
end
