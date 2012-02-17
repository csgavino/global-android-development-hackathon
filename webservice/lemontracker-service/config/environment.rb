# Load the rails application
require File.expand_path('../application', __FILE__)

# Initialize the rails application
LemontrackerService::Application.initialize!

Date::DATE_FORMATS[:foomat] = '%d/%m/%Y'
Time::DATE_FORMATS[:foomat] = '%B %d %Y - %I:%M:%S%p'