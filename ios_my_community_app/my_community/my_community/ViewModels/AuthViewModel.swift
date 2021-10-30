//
//  AuthViewModel.swift
//
//  Created by NK NK on 2021/09/27.
//

import Foundation
import KakaoSDKCommon
import KakaoSDKAuth
import KakaoSDKUser

import FirebaseFirestore

class AuthViewModel: ObservableObject {
    
    @Published var signedIn = false
    @Published var userInfo = UserInfo(id: 0, nickName: "")
//    @Published var likes = [LikeInfo]()
//    @Published var oneLike = LikeInfo(userID: "", itemName: "", value: false)
//    private var db = Firestore.firestore()
    
    init (){
        self.checkToken()
    }
    
    func checkToken() {
        if (AuthApi.hasToken()) {
            UserApi.shared.accessTokenInfo { (_, error) in
                if let error = error {
                    if let sdkError = error as? SdkError, sdkError.isInvalidTokenError() == true  {
                        //로그인 필요
                    }
                    else {
                        //기타 에러
                    }
                }
                else {
                    //토큰 유효성 체크 성공(필요 시 토큰 갱신됨)
                    self.signedIn=true
                    self.getUserInfo()
                }
            }
        }
        else {
            //로그인 필요
            self.signedIn=false
        }
    }
    
    func logOut() {
        UserApi.shared.unlink {(error) in
            if let error = error {
                print(error)
            }
            else {
                print("unlink() success.")
                self.signedIn=false
            }
        }
    }
    
    func loginByApp(){
        if (UserApi.isKakaoTalkLoginAvailable()) {
            UserApi.shared.loginWithKakaoTalk {(oauthToken, error) in
                if let error = error {
                    print(error)
                }
                else {
                    print("loginWithKakaoTalk() success.")

                    //do something
                    _ = oauthToken
                    
                    self.signedIn=true
                    self.getUserInfo()
                }
            }
        }
        
    }

    func loginByAccount() {
        
        UserApi.shared.loginWithKakaoAccount {(oauthToken, error) in
                if let error = error {
                    print(error)
                }
                else {
                    print("loginWithKakaoAccount() success.")

                    //do something
                    _ = oauthToken
                    
                    self.signedIn=true
                    self.getUserInfo()
                
                }
            }
    }
    
    func getUserInfo() {

        UserApi.shared.me() {(user, error) in
            if let error = error {
                print(error)
            }
            else {
                print("me() success.")

                //do something
                _ = user

                self.userInfo.id=user?.id ?? -1
                self.userInfo.nickName=user?.kakaoAccount?.profile?.nickname ?? "empty nickname"

//
//                self.postUserInfoToFireStore()

            }
        }

    }


//    func postUserInfoToFireStore() {
//
//        // Add a new document in collection "cities"
//        db.collection("users").document(String(userInfo.id ?? 0)).setData([
//            "id": userInfo.id,
//            "nickName": userInfo.nickName
//        ]) { err in
//            if let err = err {
//                print("Error writing document: \(err)")
//            } else {
//                print("postUserInfoToFireStore Document successfully written!")
//            }
//        }
//
//    }
//
//
//    func getLikeData(userID:String) {
//        db.collection("users").document(userID).collection("likes").addSnapshotListener { (querySnapshot, error) in
//            guard let documents = querySnapshot?.documents else {
//                print("getLikeData No documents")
//                return
//            }
//
//            self.likes = documents.map { (queryDocumentSnapshot) -> LikeInfo in
//                let data = queryDocumentSnapshot.data()
//
//                let userID = data["userID"] as? String ?? ""
//                let itemName = data["itemName"] as? String ?? ""
//                let value = data["value"] as? Bool ?? false
//
//                print("getLikeData success \(itemName) \(value)")
//                print()
//                return LikeInfo(userID: userID, itemName: itemName, value: value)
//            }
//        }
//    }
//
//    func getOneLikeData(userID:String, itemName:String) {
//        let docRef = db.collection("users").document(userID).collection("likes").document(itemName)
//
//        docRef.getDocument { (document, error) in
//            if let document = document, document.exists {
//                let dataDescription = document.data()
//
//                let temp = LikeInfo(userID: dataDescription?["userID"] as! String, itemName: dataDescription?["itemName"] as! String, value: dataDescription?["value"] as! Bool)
//
//                self.oneLike = temp
//
//                print("abc Document data: \(dataDescription)")
//
//                self.getLikeData(userID: userID)
//
//            } else {
//                print("abc Document does not exist")
//                let temp = LikeInfo(userID: userID, itemName: itemName, value: false)
//                self.oneLike = temp
//                self.getLikeData(userID: userID)
//            }
//        }
//    }
//
//
//    func postLikeData(userID: String, itemName: String ,value: Bool) {
//
//        // Add a new document in collection "cities"
//        db.collection("users").document(String(userID)).collection("likes").document(itemName).setData([
//            "userID": userID,
//            "itemName": itemName,
//            "value": value
//        ]) { err in
//            if let err = err {
//                print("postLikeData Error writing document: \(err)")
//                self.getLikeData(userID: userID)
//                self.getOneLikeData(userID: userID, itemName: itemName)
//            } else {
//                print("postLikeData  Document successfully written!")
//                self.getLikeData(userID: userID)
//                self.getOneLikeData(userID: userID, itemName: itemName)
//            }
//        }
//
//    }
//
    
}
