//
//  my_communityApp.swift
//  my_community
//
//  Created by NK NK on 2021/10/30.
//

import SwiftUI
import KakaoSDKCommon
import KakaoSDKAuth
import Firebase
import FirebaseFirestore

@main
struct my_communityApp: App {
    
    init() {
        // Kakao SDK 초기화
        KakaoSDKCommon.initSDK(appKey: MyKey.kakao)
        FirebaseApp.configure()
    }
    
    var body: some Scene {
        WindowGroup {
            let authViewModel = AuthViewModel()
            
            // onOpenURL()을 사용해 커스텀 스킴 처리
            InitialView()
                .onOpenURL(perform: { url in
                if (AuthApi.isKakaoTalkLoginUrl(url)) {
                    AuthController.handleOpenUrl(url: url)
                }
            })
                .environmentObject(authViewModel)
        }
    }
}
