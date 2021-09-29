//
//  DetailView.swift
//  api_test (iOS)
//
//  Created by NK NK on 2021/08/12.
//


import SwiftUI

struct DetailView: View {
    
    let city : City

    
    var body: some View {
        VStack{
            Text(city.name)
            ForEach(city.dateWeather[0].hour, id: \.hour) { hw in
                VStack {
                    if hw.rain > 30 {
                        Text("비")
                    }
                    else if hw.sky > 3 {
                        Text("흐림")
                    }
                    else {
                        Text("맑음")
                    }



                }
                
            }
            Text(city.dateWeather[0].hour[0].nowTmp)
            
            
            ScrollView(.horizontal){

                ForEach(city.dateWeather, id: \.date) { dw in

                    ForEach(dw.hour, id: \.hour) { hw in
                        VStack {
                            if hw.rain > 30 {
                                Image(systemName: "cloud.rain.fill")
                                    .foregroundColor(.blue)
                            }
                            else if hw.sky > 3 {
                                Image(systemName: "cloud.fill")
                                    .foregroundColor(.gray)
                            }
                            else {
                                Image(systemName: "sun.max.fill")
                                    .foregroundColor(.red)
                            }

                            Text("\(dw.date)")
                            Text("\(hw.hour)")
                            Text("\(hw.nowTmp)")
                            Text("\(hw.rain)")
                        }
                    }
                }
                    
            }//scroll view end
            
            Text(city.dateWeather[0].hour[0].nowTmp)
            
            
        }

    }
}
