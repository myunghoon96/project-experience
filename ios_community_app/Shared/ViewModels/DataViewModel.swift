//
//  DataViewModel.swift
//  community_sample (iOS)
//
//  Created by NK NK on 2021/09/21.
//

import FirebaseFirestore

class DataViewModel: ObservableObject {
    
    @Published var itemInfos = [ItemInfo]()

    private var db = Firestore.firestore()
    
    func fetchAllItems() {
        db.collection("Items").addSnapshotListener { (querySnapshot, error) in
            guard let documents = querySnapshot?.documents else {
                print("No documents")
                return
            }
            
            self.itemInfos = documents.map { (queryDocumentSnapshot) -> ItemInfo in
                let data = queryDocumentSnapshot.data()

                let name = data["name"] as? String ?? ""
                let detail = data["detail"] as? String ?? ""

                return ItemInfo(name: name, detail: detail)
            }
        }
    }
    
}
