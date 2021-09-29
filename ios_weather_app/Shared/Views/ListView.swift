//
//  ListView.swift
//  Shared
//
//  Created by NK NK on 2021/08/12.
//

import SwiftUI

struct ListView: View {
    @StateObject var viewModel = ViewModdel()
    
    
    
    var body: some View {
        NavigationView{
            
            //하늘상태(SKY) 코드 : 맑음(1), 구름많음(3), 흐림(4)
            //강수형태(PTY) 코드 (단기) 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4)
//            예보구분    항목값    항목명    단위    압축bit수
//            단기예보    POP    강수확률    %    8
//                PTY    강수형태    코드값    4
//                PCP    1시간 강수량    범주 (1 mm)    8
//                REH    습도    %    8
//                SNO    1시간 신적설    범주(1 cm)    8
//                SKY    하늘상태    코드값    4
//                TMP    1시간 기온    ℃    10
//                TMN    일 최저기온    ℃    10
//                TMX    일 최고기온    ℃    10
//                UUU    풍속(동서성분)    m/s    12
//                VVV    풍속(남북성분)    m/s    12
//                WAV    파고    M    8
//                VEC    풍향    deg    10
//                WSD    풍속    m/s    10

            List {
                ForEach(viewModel.cities, id: \.id) { city in
 
                    ScrollView(.horizontal){
                        NavigationLink(
                            destination: DetailView(city: city),
                            label: {
                                HStack{
                                    Text(city.name)
                                    
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

//                                                    Text("\(dw.date)")
                                                    Text("\(hw.hour)")
                                                    Text("\(hw.nowTmp)°C")
                                                    Text("\(hw.rain)%")
                                                }
                                            }
                                        }
                                    
                                }.foregroundColor(.black)
                                
                            })
                            


                    }
                }
            }
            
            .navigationTitle("기상청 날씨 API")
            
        }
    
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ListView()
    }
}
