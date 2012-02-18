class CreateLocations < ActiveRecord::Migration
  def change
    create_table :locations do |t|
      t.float :longitude
      t.float :latitude
      t.string :transaction_id

      t.timestamps
    end
  end
end
