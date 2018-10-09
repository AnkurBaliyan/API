require 'net/http'
require 'yaml'
config = YAML.load_file('config.yml')
uri = URI.parse(config['url'])
request = Net::HTTP::Post.new(uri)
request.basic_auth(config['userName'], config['password'])
request.set_form_data('number' => config['number'])
response = Net::HTTP.start(uri.hostname, uri.port, use_ssl: uri.scheme == 'https') do |http|
response=http.request(request)
puts 'Response Code : ' + response.code
puts response.body
end
