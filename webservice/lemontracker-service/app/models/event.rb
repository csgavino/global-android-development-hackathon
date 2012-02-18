class Event < ActiveRecord::Base
  attr_accessible :name, :blurb, :description, :date_start, :date_end, :thumb_url, :image_url, :longitude, :latitude, :category_id
  belongs_to :category
  
end
