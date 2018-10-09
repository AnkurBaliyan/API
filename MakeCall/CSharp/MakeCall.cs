using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Configuration;

namespace MakeCallApi {
    class MakeCall {
        public static void Main(String[] args) {
            using (HttpClient httpClient = new HttpClient()) {
                var url = ConfigurationManager.AppSettings.Get("url");
                var userName = ConfigurationManager.AppSettings.Get("userName");
                var password = ConfigurationManager.AppSettings.Get("password");
                var number = ConfigurationManager.AppSettings.Get("number");
                httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Basic",Convert.ToBase64String(System.Text.ASCIIEncoding.ASCII.GetBytes(userName+":"+password)));
                var content = new FormUrlEncodedContent( new List<KeyValuePair<string, string>>() {
                    new KeyValuePair<string,string>("number", number)
                });
                var request = new HttpRequestMessage(HttpMethod.Post, url);
                request.Content = content;
                var response =  httpClient.SendAsync(request, HttpCompletionOption.ResponseHeadersRead).Result;
                Console.WriteLine("Response Dode : " + (int)response.StatusCode);
                Console.WriteLine(response.Content.ReadAsStringAsync().Result);
            }
        }
    }
}
