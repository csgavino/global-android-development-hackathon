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

              
Event.create( :name => "SM City Iloilo 3 Day Sale", 
              :description => "March 18-20, 2012 -  SM City Iloilo 3-day SALE!  Get up to 70% off on great selections! All SM Advantage and BDO Rewards card holders will get additional 10% OFF* at SM City Iloilo from 10am to 12mn!", 
              :blurb => "Get up to 70% off on great selections!",
              :date_start => DateTime.civil(2012, 3, 18, 10, 0, 0).in_time_zone('Asia/Taipei'), 
              :date_end => DateTime.civil(2012, 3, 20, 23, 59, 59).in_time_zone('Asia/Taipei'),
              :thumb_url =>  "/images/thumbs/1.jpg", 
              :image_url =>  "/images/event_banners/1.jpg", 
              :longitude => 122.550473, 
              :latitude => 10.715230, 
              :category_id => 3)
              
              
Event.create( :name => "SM North Edsa 3 Day Sale", 
              :description => "February 3-5, 2012 - SM North Edsa 3-day SALE!  Get up to 70% off on great selections! All SM Advantage and BDO Rewards card holders will get additional 10% OFF* at the 2-Hour Special on Friday February 3, 2011 at SM North EDSA from 10am to 12nn!", 
              :blurb => "Get up to 70% off on great selections!",
              :date_start => DateTime.civil(2012, 2, 17, 10, 0, 0).in_time_zone('Asia/Taipei'), 
              :date_end => DateTime.civil(2012, 2, 19, 23, 59, 59).in_time_zone('Asia/Taipei'), 
              :thumb_url => "/images/thumbs/2.jpg", 
              :image_url => "/images/event_banners/2.jpg", 
              :longitude => 121.031249, 
              :latitude => 14.656440, 
              :category_id => 3)
              
Event.create( :name => "DEATH CAB for CUTIE Concert", 
              :description => "American alternative rock band Death Cab for Cutie (DCFC) will be having a LIVE concert here in Manila, Philippines on March 5, 2012, 7pm NBC Tent, Global City, Taguig as confirmed by Rico Blanco through a Facebook post.", 
              :blurb => "LIVE concert, 7pm NBC Tent, Global City, Taguig, Metro Manila, Philippines",
              :date_start => DateTime.civil(2012,3, 5, 19, 0, 0).in_time_zone('Asia/Taipei'), 
              :date_end => DateTime.civil(2012, 3, 5, 23, 59, 59).in_time_zone('Asia/Taipei'), 
              :thumb_url => "/images/thumbs/3.jpg", 
              :image_url => "/images/event_banners/3.jpg", 
              :longitude => 121.049359, 
              :latitude => 14.548735, 
              :category_id => 1)
              
Event.create( :name => "PENSHOPPE Trinoma Sale", 
              :description => "Enjoy up to 30% off on Penshoppe's!  ALL items are on sale from April 10-12, 2012! 30% off on selected women's styles, 20% off on selected men's styles and 30% off on all body sprays.", 
              :blurb => "Enjoy up to 30% off on Penshoppe's!  ALL items are on sale from April 10-12, 2012!",
              :date_start => DateTime.civil(2012,4, 10, 10, 0, 0).in_time_zone('Asia/Taipei'), 
              :date_end => DateTime.civil(2012, 4, 12, 23, 59, 59).in_time_zone('Asia/Taipei'), 
              :thumb_url => "/images/thumbs/4.jpg", 
              :image_url => "/images/event_banners/4.jpg", 
              :longitude => 121.033523,
              :latitude => 14.653015, 
              :category_id => 3)

Event.create( :name => "Status Magazine Yard Sale", 
              :description => "Status Magazine yard sale at Embassy in The Fort, Taguig on August 1, 2012. Entrance fee is Php100 and the sale is open from 11am to 7pm.", 
              :blurb => "Status Magazine yard sale at Embassy in The Fort, Taguig on August 1, 2012.",
              :date_start => DateTime.civil(2012, 8, 1, 11, 0, 0).in_time_zone('Asia/Taipei'), 
              :date_end => DateTime.civil(2012, 8, 1, 19, 0, 0).in_time_zone('Asia/Taipei'), 
              :thumb_url => "/images/thumbs/5.jpg", 
              :image_url => "/images/event_banners/5.jpg", 
              :longitude => 121.047894,
              :latitude => 14.549794, 
              :category_id => 3)
              

              
                      