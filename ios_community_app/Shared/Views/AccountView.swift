//
//  AccountView.swift
//  community_sample (iOS)
//
//  Created by NK NK on 2021/09/21.
//

import SwiftUI

struct AccountView: View {
    
    @EnvironmentObject var authViewModel: AuthViewModel
    
    var body: some View {
        VStack{
            Text("Account View")
            Text("로그인 성공!")
                .padding()
            Text("고유 아이디: \(String(authViewModel.userInfo.id))" )

            Text("닉네임: \(authViewModel.userInfo.nickName)")

                .padding()
            Button(action: {
                authViewModel.logOut()
            }, label: {
                Text("로그아웃")
            })
        }
        
    }
}

struct AccountView_Previews: PreviewProvider {
    static var previews: some View {
        AccountView()
    }
}

