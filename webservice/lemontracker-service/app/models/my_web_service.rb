class MyWebService

  WSDL = "http://iplaypen.globelabs.com.ph:1881/axis2/services/Platform?wsdl"

  def self.client
    @@client ||= Savon::Client.new(WSDL)
  end

  def self.soap_actions
    return client.wsdl.soap_actions
  end

  def self.invoke(action, parameters)
    response = client.request(action) { |soap| soap.body = parameters }
    return response.to_hash
  end

end