//
//  DataViewModel.swift
//
//  Created by NK NK on 2021/09/27.
//

import FirebaseFirestore

class DataViewModel: ObservableObject {
    
    @Published var itemInfos = [ItemInfo]()
    @Published var notices = [NoticeInfo]()

    
    private var db = Firestore.firestore()
    
    func getAllItems() {
        db.collection("Items").addSnapshotListener { (querySnapshot, error) in
            guard let documents = querySnapshot?.documents else {
                print("No documents")
                return
            }
            
            self.itemInfos = documents.map { (queryDocumentSnapshot) -> ItemInfo in
                let data = queryDocumentSnapshot.data()

                let name = data["name"] as? String ?? ""
                let detail = data["detail"] as? String ?? ""
//                print(name)
//                print(detail)
                //print(tags)
                return ItemInfo(name: name, detail: detail)
            }
        }
    }
    

    func getNoticeData() {
        db.collection("notices").addSnapshotListener { (querySnapshot, error) in
            guard let documents = querySnapshot?.documents else {
                print("No documents")
                return
            }

            self.notices = documents.map { (queryDocumentSnapshot) -> NoticeInfo in
                let data = queryDocumentSnapshot.data()

                let title = data["title"] as? String ?? ""
                let content = data["content"] as? String ?? ""
                let user = data["user"] as? String ?? ""
                
                //print(tags)
                return NoticeInfo(title: title, content: content, writer: user)
            }
        }
    }
    

    func postNoticeData(user: String, title: String, content: String) {

        // Add a new document in collection
        db.collection("notices").document().setData([
            "title": title,
            "content": content,
            "writer": user
        ]) { err in
            if let err = err {
                print("Error writing document: \(err)")
            } else {
                print("postNoticeData Document successfully written!")
            }
        }

    }
    
}
