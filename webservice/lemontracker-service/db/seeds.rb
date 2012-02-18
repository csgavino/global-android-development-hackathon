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
              :thumb_url =>  "images/thumbs/1.jpg", 
              :image_url =>  "images/event_banners/1.jpg", 
              :longitude => 122.550473, 
              :latitude => 10.715230, 
              :category_id => 3)
              
              
Event.create( :name => "SM North Edsa 3 Day Sale", 
              :description => "February 3-5, 2012 - SM North Edsa 3-day SALE!  Get up to 70% off on great selections! All SM Advantage and BDO Rewards card holders will get additional 10% OFF* at the 2-Hour Special on Friday February 3, 2011 at SM North EDSA from 10am to 12nn!", 
              :blurb => "Get up to 70% off on great selections!",
              :date_start => DateTime.civil(2012, 2, 17, 10, 0, 0).in_time_zone('Asia/Taipei'), 
              :date_end => DateTime.civil(2012, 2, 19, 23, 59, 59).in_time_zone('Asia/Taipei'), 
              :thumb_url => "images/thumbs/2.jpg", 
              :image_url => "images/event_banners/2.jpg", 
              :longitude => 121.031249, 
              :latitude => 14.656440, 
              :category_id => 3)
              
Event.create( :name => "DEATH CAB for CUTIE Concert", 
              :description => "American alternative rock band Death Cab for Cutie (DCFC) will be having a LIVE concert here in Manila, Philippines on March 5, 2012, 7pm NBC Tent, Global City, Taguig as confirmed by Rico Blanco through a Facebook post.", 
              :blurb => "LIVE concert, 7pm NBC Tent, Global City, Taguig, Metro Manila, Philippines",
              :date_start => DateTime.civil(2012,3, 5, 19, 0, 0).in_time_zone('Asia/Taipei'), 
              :date_end => DateTime.civil(2012, 3, 5, 23, 59, 59).in_time_zone('Asia/Taipei'), 
              :thumb_url => "images/thumbs/3.jpg", 
              :image_url => "images/event_banners/3.jpg", 
              :longitude => 121.049359, 
              :latitude => 14.548735, 
              :category_id => 1)
              
Event.create( :name => "PENSHOPPE Trinoma Sale", 
              :description => "Enjoy up to 30% off on Penshoppe's!  ALL items are on sale from April 10-12, 2012! 30% off on selected women's styles, 20% off on selected men's styles and 30% off on all body sprays.", 
              :blurb => "Enjoy up to 30% off on Penshoppe's!  ALL items are on sale from April 10-12, 2012!",
              :date_start => DateTime.civil(2012,4, 10, 10, 0, 0).in_time_zone('Asia/Taipei'), 
              :date_end => DateTime.civil(2012, 4, 12, 23, 59, 59).in_time_zone('Asia/Taipei'), 
              :thumb_url => "images/thumbs/4.jpg", 
              :image_url => "images/event_banners/4.jpg", 
              :longitude => 121.033523,
              :latitude => 14.653015, 
              :category_id => 3)

Event.create( :name => "Status Magazine Yard Sale", 
              :description => "Status Magazine yard sale at Embassy in The Fort, Taguig on February 15-21, 2012. Entrance fee is Php100 and the sale is open from 11am to 7pm.", 
              :blurb => "Status Magazine yard sale at Embassy in The Fort, Taguig on February 15-21, 2012.",
              :date_start => DateTime.civil(2012, 2, 15, 11, 0, 0).in_time_zone('Asia/Taipei'), 
              :date_end => DateTime.civil(2012, 2, 21, 19, 0, 0).in_time_zone('Asia/Taipei'), 
              :thumb_url => "images/thumbs/5.jpg", 
              :image_url => "images/event_banners/5.jpg", 
              :longitude => 121.047894,
              :latitude => 14.549794, 
              :category_id => 3)
              
Event.create( :name => "Avril Lavigne Concert", 
              :description => "Canadian singer Avril Lavigne is coming back to Manila for The Black Star tour to rock again the stage of Smart Araneta Coliseum on February 19, 2012. Avril Lavigne better known by her birth name of Avril Ramona Lavigne born in September 27, 1984 is a Canadian Grammy award-nominated pop punk singer, musician, fashion designer and actress. In 2006, Canadian Business Magazine ranked her the seventh most powerful Canadian in Hollywood. Having sold close to 30 million albums worldwide, Avril Lavigne is quickly paving her own path to rock n roll royalty.  Three multi-platinum albums, Let Go, Under My Skin and Best Damn Thing Lavigne scored a string of international hit singles: Complicated, Sk8er Boi, Im With You, Losing Grip, Dont Tell Me, My Happy Ending, Nobodys Home, Keep Holding On, Girlfriend, When Youre Gone, Hot, and The Best Damn Thing. She earned eight Grammy Award nominations for her debut album Let Go and has sold 16 Million copies worldwide. She also won seven Canadian Juno Awards, and sold nearly 20 million tracks worldwide over the past eight years. Her 2004 sophomore effort, Under My Skin debuted at #1 around the world, as did her much anticipated third album, Best Damn Thing.  Shes coming back with her brand new hit album Goodbye Lullaby including smash hits What The Hell, Smile and Wish You Were Here. Dont miss The Black Star Tour: Avril Lavigne live in Manila on February 19, 2012. Limited VIP Tickets Call Wilbros Live at 374-2222
              TICKET PRICES:
              Patron Center  P7920
              Patron Sides  P6340
              Lower Box  P5280
              Upper Box A  P2640
              Upper Box B  P1585
              Gen Admission  P530

              For ticket inquiries you may call Wilbros Live! 374 2222 or Ticketnet 911 5555

              Brought to you by Wilbros Live!", 
              :blurb => "Avril Lavigne Black Star Tour",
              :date_start => DateTime.civil(2012, 2, 19, 20, 0, 0).in_time_zone('Asia/Taipei'), 
              :date_end => DateTime.civil(2012, 2, 19, 23, 0, 0).in_time_zone('Asia/Taipei'), 
              :thumb_url => "images/thumbs/6.jpg", 
              :image_url => "images/event_banners/6.jpg", 
              :longitude => 121.053451,
              :latitude => 14.620748, 
              :category_id => 1)   


Event.create( :name => "Toyota Tent Event", 
              :description => "In celebration of its 23rd anniversary, Toyota Motor Philippines is bringing back the biggest car sales event of the year. The countrys leading carmaker is holding the Toyota Tent Event on August 18 to 23 at the NBC Tent in Bonifacio Global City. As in the past, this selling event offers a perfect opportunity for Toyota customers to avail of exclusive offers and packages, attractive tradein deals and exciting activities for the entire family.

              The Toyota Tent Event is our way of showing our sincerest appreciation to our customers, says TMP executive vice president Yuji Goto. In doing this, I believe we will be able to provide Filipino car buyers with the best products at the most affordable prices.", 
              :blurb => "Status Magazine yard sale at Embassy in The Fort, Taguig on August 1, 2012.",
              :date_start => DateTime.civil(2012, 8, 18, 11, 0, 0).in_time_zone('Asia/Taipei'), 
              :date_end => DateTime.civil(2012, 8, 23, 19, 0, 0).in_time_zone('Asia/Taipei'), 
              :thumb_url => "images/thumbs/7.jpg", 
              :image_url => "images/event_banners/7.jpg", 
              :longitude => 121.049359,
              :latitude => 14.548735, 
              :category_id => 5)
              
              
Event.create( :name => "McDonalds Free Coffee", 
              :description => "Get a free 8 oz. McCafe Premium Roast Coffee from February 16 to 21 2012 at any McDonalds breakfast store from 4:00 a.m. to 10:30 a.m.
                Be on the lookout for the McDonalds crew who will distribute the coupons in selected offices, universities, colleges, terminals, churches and even outside the McDonalds stores nationwide starting August 5!",
              :blurb => "Get a free 8 oz. McCafe Premium Roast Coffee from February 16 to 21 2012 at any McDonalds breakfast store from 4:00 a.m. to 10:30 a.m.",
              :date_start => DateTime.civil(2012, 2, 16, 4, 0, 0).in_time_zone('Asia/Taipei'), 
              :date_end => DateTime.civil(2012, 2, 21, 10, 30, 0).in_time_zone('Asia/Taipei'), 
              :thumb_url => "images/thumbs/8.jpg", 
              :image_url => "images/event_banners/8.jpg", 
              :longitude => 121.019881,
              :latitude => 14.557312, 
              :category_id => 2)
              
Event.create( :name => "San Miguel Oktoberfest", 
              :description => "The main attraction in this years Oktoberfest Beer Festival is the 30 foot Beer Pyramid which symbolizes the fresh and great taste of San Miguel Beer. Since this year will be a celebration of San Miguel Beer, all brands of beer will be sold for Php 10.00 only! In addition to that, each brand will have its own tent which will house different performers and beer drinking games.",
              :blurb => "Maki party na! P10.00 per cup lang ang beer sa mga KickOff parties!",
              :date_start => DateTime.civil(2012, 10, 1, 18, 0, 0).in_time_zone('Asia/Taipei'), 
              :date_end => DateTime.civil(2012, 10, 7, 2, 30, 0).in_time_zone('Asia/Taipei'), 
              :thumb_url => "images/thumbs/9.jpg", 
              :image_url => "images/event_banners/9.jpg", 
              :longitude => 121.024189,
              :latitude => 14.557032, 
              :category_id => 4)              

              
                      