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
              :date_start => DateTime.civil(2012, 3, 18, 10, 0, 0, Rational(-6, 24)).in_time_zone('Asia/Taipei'), 
              :date_end => DateTime.civil(2012, 3, 20, 11, 59, 59, Rational(-6, 24)).in_time_zone('Asia/Taipei'),
              :thumb_url =>  "/public/images/thumbs/1.jpg", 
              :image_url =>  "/public/images/event_banners/1.jpg", 
              :longitude => 122.550473, 
              :latitude => 10.715230, 
              :category_id => 3)
              
              
Event.create( :name => "SM North Edsa 3 Day Sale", 
              :description => "February 3-5, 2012 - SM North Edsa 3-day SALE!  Get up to 70% off on great selections! All SM Advantage and BDO Rewards card holders will get additional 10% OFF* at the 2-Hour Special on Friday February 3, 2011 at SM North EDSA from 10am to 12nn!", 
              :blurb => "Get up to 70% off on great selections!",
              :date_start => DateTime.civil(2012, 2, 17, 10, 0, 0, Rational(-6, 24)).in_time_zone('Asia/Taipei'), 
              :date_end => DateTime.civil(2012, 2, 19, 11, 59, 59, Rational(-6, 24)).in_time_zone('Asia/Taipei'), 
              :thumb_url => "/public/images/thumbs/2.jpg", 
              :image_url => "/public/images/event_banners/2.jpg", 
              :longitude => 121.031249, 
              :latitude => 14.656440, 
              :category_id => 3)

                      