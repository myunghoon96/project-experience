//
//  ViewModel.swift
//  api_test (iOS)
//
//  Created by NK NK on 2021/08/12.
//

import Foundation
import SwiftUI

class ViewModdel: ObservableObject {

    @Published var cities: [City] = []
    var locations : [Location] = []
    
    init () {
        let seoul = Location(name: "서울", nx: "60", ny: "27")
        let incheon = Location(name: "인천", nx: "55", ny: "124")
        let busan = Location(name: "부산", nx: "98", ny: "76")
        locations=[seoul, incheon, busan]
        print(locations)
        for i in 0...2 {
            fetch(l: locations[i])
        }

        
    }
    
    func fetch(l:Location) {
        let x=l.nx
        let y=l.ny
        var city = City(name: l.name, dateWeather: [], hourWeather: [])
        
        let now_date = Date()
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyyMMdd"
        let dateFormatter2 = DateFormatter()
        dateFormatter2.dateFormat = "HHmm"

        let now_time = Int(dateFormatter2.string(from: now_date)) ?? 0
 
        let b_date = dateFormatter.string(from: now_date)
        var b_time = ""
        print("now_date \(now_date)")
        print("b_date \(b_date)")

        print("now_time \(now_time)")
        
        switch now_time {
        case 0...230:
            print("pre2300")
            b_time="0500"
            break
        case 230...530:
            print("0200")
            b_time="0200"
            break
        case 530...830:
            print("0500")
            b_time="0500"
            break
        case 830...1130:
            print("0800")
            b_time="1100"
            break
        case 1130...1430:
            print("1100")
            b_time="1100"
            break
        case 1430...1730:
            print("1400")
            b_time="1400"
            break
        case 1730...2030:
            print("1700")
            b_time="1700"
            break
        case 2030...2359:
            print("2000")
            b_time="2000"
            break
        default:
            print("default")
            b_time="0500"

        }
        print("b_time \(b_time)")

        guard let url = URL(string: "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey=\(MyKey.weather_api_key)&pageNo=1&numOfRows=50&dataType=JSON&base_date=\(b_date)&base_time=\(b_time)&nx=\(x)&ny=\(y)") else { return }
    

        let task = URLSession.shared.dataTask(with: url) { data, _, error in
            guard let data = data, error == nil else {
                return
            }
            do {
                let json = try JSONDecoder().decode(Initial.self, from: data)
                print("A")
                let jsonInfos = json.response.body.items.item
                      
                var dateList : [String] = []
                
                var dateWeatherList : [DateWeather] = []
                
                for item in jsonInfos{

                    if dateList.contains(item.fcstDate) {
                        //dateWeather struct already exist
                    }
                    else {
                        let dateWeather = DateWeather(date: item.fcstDate, minTmp: item.fcstValue, maxTmp: "", rain: [], hour: [])
                        dateWeatherList.append(dateWeather)
                        dateList.append(item.fcstDate)
                    }
  

                    
                    //시간 기온
                    if item.category=="TMP" {
                        print("\(item.fcstDate) \(item.category) \(item.fcstTime) \(item.fcstValue)")
                        for (i, dw) in dateWeatherList.enumerated() {
                            if dw.date==item.fcstDate {
                                dateWeatherList[i].hour.append(HourWeather(hour: item.fcstTime, nowTmp: item.fcstValue, rain: 0, sky: 0))
                            }
                        }
                    }
                    
                    //하늘 상태
                    else if item.category=="SKY" {
                        print("\(item.fcstDate) \(item.category) \(item.fcstTime) \(item.fcstValue)")
                        for (i, dw) in dateWeatherList.enumerated() {
                            if dw.date==item.fcstDate {
                                for (j, hw) in dateWeatherList[i].hour.enumerated() {
                                    if hw.hour == item.fcstTime {
                                        dateWeatherList[i].hour[j].sky = Int(item.fcstValue) ?? 0
                                    }
                                }
                            }
                        }
                    }

                    //강수확률
                    else if item.category=="POP" {
                        print("\(item.fcstDate) \(item.category) \(item.fcstTime) \(item.fcstValue)")
                        for (i, dw) in dateWeatherList.enumerated() {
                            if dw.date==item.fcstDate {
                                dateWeatherList[i].rain.append(Int(item.fcstValue) ?? 0)
                                for (j, hw) in dateWeatherList[i].hour.enumerated() {
                                    if hw.hour == item.fcstTime {
                                        dateWeatherList[i].hour[j].rain = Int(item.fcstValue) ?? 0
                                    }
                                    
                                }
                                
                                
                            }
                        }
                    }
                    
                    //최저 기온
                    else if item.category=="TMN" {
                        print("\(item.fcstDate) \(item.category) \(item.fcstTime) \(item.fcstValue)")
                        for (i, dw) in dateWeatherList.enumerated() {
                            if dw.date==item.fcstDate {
                                dateWeatherList[i].minTmp=item.fcstValue

                            }
                        }
                    }
                    
                    //최고 기온
                    else if item.category=="TMX" {
                        print("\(item.fcstDate) \(item.category) \(item.fcstTime) \(item.fcstValue)")
                        for (i, dw) in dateWeatherList.enumerated() {
                            if dw.date==item.fcstDate {
                                dateWeatherList[i].maxTmp=item.fcstValue
                            }
                        }
                    }
                    

                    
                }
                
 
                city.dateWeather=dateWeatherList
                for a in city.dateWeather {
                    print(a.rain.max() ?? 0)
                    
                }
                
                
                DispatchQueue.main.async {
//                    self.infos = jsonInfos
                    self.cities.append(city)
                }
            }
            catch {
                print("B")
                print(error)

            }


        }
        task.resume()
    }
}

