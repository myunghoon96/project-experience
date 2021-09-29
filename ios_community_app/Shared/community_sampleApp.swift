//
//  community_sampleApp.swift
//  Shared
//
//  Created by NK NK on 2021/09/20.
//

import SwiftUI
import KakaoSDKCommon
import KakaoSDKAuth
import Firebase
import FirebaseFirestore

@main
struct community_sampleApp: App {
    
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
