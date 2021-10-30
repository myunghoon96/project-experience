//
//  SearchView.swift
//
//  Created by NK NK on 2021/09/27.
//

import SwiftUI



struct SearchView: View {
    @State var search_query: String = ""
    var body: some View {
        VStack{
            HStack{
                Spacer()
                TextField("검색어", text: $search_query)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .foregroundColor(.blue)
                    .overlay(RoundedRectangle(cornerRadius: 10).stroke(Color.blue, lineWidth: 1))
                Image(systemName: "magnifyingglass")
                Spacer()
            }
            Spacer()
        }
        .padding(10)

        
    }
}

struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView()
    }
}
