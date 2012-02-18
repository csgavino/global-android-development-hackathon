class EditEventsModel < ActiveRecord::Migration
  def change
    add_column :events, :blurb, :string
    change_column :events, :longitude, :float
    change_column :events, :latitude, :float
  end
end
