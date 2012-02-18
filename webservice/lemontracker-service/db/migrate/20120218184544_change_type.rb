class ChangeType < ActiveRecord::Migration
  def change
    change_column :locations, :longitude, :string
    change_column :locations, :latitude, :string
  end

end
