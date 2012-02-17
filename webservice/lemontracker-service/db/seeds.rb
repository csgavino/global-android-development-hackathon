# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create(name: 'Emanuel', city: cities.first)


Category.create(:name => "Concerts")
Category.create(:name => "Restaurants")
Category.create(:name => "Sales")
Category.create(:name => "Parties")
Category.create(:name => "Others")

Event.create( :name => "Concert 1", 
              :description => "", 
              :date_start => DateTime.now, 
              :date_end => DateTime.now, 
              :thumb_url => "", 
              :image_url => "", 
              :longitude => 1, 
              :latitude => 1, 
              :category_id => 1)
              
Event.create( :name => "Concert 2", 
              :description => "", 
              :date_start => DateTime.now, 
              :date_end => DateTime.now, 
              :thumb_url => "", 
              :image_url => "", 
              :longitude => 1, 
              :latitude => 1, 
              :category_id => 1)

                      