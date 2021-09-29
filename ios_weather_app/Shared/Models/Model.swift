//
//  Model.swift
//  api_test (iOS)
//
//  Created by NK NK on 2021/08/12.
//

import Foundation
import SwiftUI

struct Initial: Codable {
    let response: Response
}

struct Response: Codable {
    let header: Header
    let body: Body
}

struct Header: Codable {
    let resultCode: String
    let resultMsg: String
}

struct Body: Codable {
    let dataType: String
    let items: Item
    let pageNo: Int
    let numOfRows: Int
    let totalCount: Int
}

struct Item: Codable {
    let item:[Info]
}

struct Info: Hashable, Codable {
    let baseDate: String
    let baseTime: String
    let category: String
    let fcstDate: String
    let fcstTime: String
    let fcstValue: String
    let nx: Int
    let ny: Int
}

struct Location: Codable {
    let name: String
    let nx: String
    let ny: String
}

struct City: Identifiable, Codable {
    var id = UUID()
    var name: String
    var dateWeather: [DateWeather]
    var hourWeather: [HourWeather]
}

struct DateWeather: Codable {
    var date: String
    var minTmp: String
    var maxTmp: String
    var rain: [Int]
    var hour: [HourWeather]
}

struct HourWeather: Codable {
    var hour: String
    var nowTmp: String
    var rain: Int
    var sky: Int
}
