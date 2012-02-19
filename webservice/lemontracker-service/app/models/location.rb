class Location < ActiveRecord::Base
  attr_accessible :longitude, :latitude, :transaction_id
end
