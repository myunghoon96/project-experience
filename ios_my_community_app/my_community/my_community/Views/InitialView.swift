//
//  InitialView.swift
//
//  Created by NK NK on 2021/09/27.
//

import SwiftUI
import KakaoSDKCommon
import KakaoSDKAuth
import KakaoSDKUser

struct InitialView: View {
    @EnvironmentObject var authViewModel: AuthViewModel
    
    var body: some View {
        
        NavigationView {
            
            if authViewModel.signedIn == true {
                MainView()
            }
            
            else {
                LoginView()
            }
            
        }

    }
}

struct LoginView: View {
    
    @EnvironmentObject var authViewModel: AuthViewModel
    
    var body: some View {
        
        VStack{
            Text("로그인 화면")
                .padding()
            
            Button(action: {
                authViewModel.loginByApp()
            }, label: {
                Image("kakao_login_medium_narrow")
            })
            .padding()
            
            Button(action: {
                authViewModel.loginByAccount()
            }, label: {
                Text("카카오 계정 로그인")
            })
            .padding()
        }

    
    }
}


