//
//  MainView.swift
//
//  Created by NK NK on 2021/09/27.
//

import SwiftUI

struct MainView: View {
    var body: some View {
        
        TabView {
            SearchView()
                .tabItem {
                    Image(systemName: "magnifyingglass")
                    Text("검색")
                }

            LikeView()
                .tabItem {
                    Image(systemName: "heart")
                    Text("좋아요")
                }
            
            ItemView()
                .tabItem {
                    Image(systemName: "list.bullet")
                    Text("아이템")
                }
            
            BoardView()
                .tabItem {
                    Image(systemName: "pencil")
                    Text("게시판")
                }
            
            AccountView()
                .tabItem {
                    Image(systemName: "person")
                    Text("계정")
                }
            
        }
        
        
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}
