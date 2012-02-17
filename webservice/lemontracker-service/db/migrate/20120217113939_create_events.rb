class CreateEvents < ActiveRecord::Migration
  def change
    create_table :events do |t|
      t.datetime :date_start
      t.datetime :date_end
      t.string :thumb_url
      t.string :image_url
      t.string :name
      t.string :description
      t.integer :category_id
      t.integer :longitude
      t.integer :latitude

      t.timestamps
    end
  end
end
