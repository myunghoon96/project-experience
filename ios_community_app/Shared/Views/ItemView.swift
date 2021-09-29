//
//  ItemView.swift
//  community_sample (iOS)
//
//  Created by NK NK on 2021/09/21.
//

import SwiftUI

struct ItemView: View {
    
    @ObservedObject private var dataViewModel = DataViewModel()
    
    var body: some View {


        List (dataViewModel.itemInfos, id: \.name) { item in

            HStack{
                Text("name: \(item.name)  ").font(.title)
                Text("detail: \(item.detail)").font(.title)
                
            }
        }
        .onAppear{dataViewModel.fetchAllItems()}

        
    }
}

struct ItemView_Previews: PreviewProvider {
    static var previews: some View {
        ItemView()
    }
}
