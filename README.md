# project-experience
### 1. 맥주 평가 앱 / 팀 프로젝트 / 2021  
팀 프로젝트로 3인이 팀을 이루어서 SwiftUI를 사용하여 IOS 앱을 제작하였다. 카카오 로그인 API, Firebase 데이터 베이스를 사용하였다.  

### 2. 커뮤니티 앱 / 개인 프로젝트 / 2021  
개인 프로젝트로 SwiftUI를 사용하여 IOS 앱을 제작하였다. 카카오 로그인 API, Firebase 데이터 베이스를 사용하였다.  

### 3. 기상청 날씨 앱 / 개인 프로젝트 / 2021  
개인 프로젝트로 SwiftUI를 사용하여 IOS 앱을 제작하였다. 기상청 단기 예보 조회 API를 사용하였다.  

### 4. 영상물 등급 판별 자동화 / 팀 프로젝트 / 2021  
팀 프로젝트로 3인이 팀을 이루어서 진행하였으며, 데이터는 영상물과 자막 파일을 이용하였다. down-sampling, subtext processing, object detection, image caption, profanity check등 기술을 사용하였다.

### 5. 이미지 장소 분류 / 개인 프로젝트 / 2021  
개인 프로젝트로 진행하였으며 데이터로 사진의 이미지 정보, gps 정보, 태그 정보를 이용하였다.
gps 정보들을 기반으로 mean-shift 알고리즘을 사용하여 클러스터링 하였으며, SIFT를 이용하여 사진들의 유사도를 기반으로 그래프를 형성하여 서브 그래프들을 하나의 클러스터로 간주하였고, 사진들의 태그 정보를 텍스트 유사도를 기반으로 분류하였다.

### 6. 자바 TCP 채팅 / 팀 프로젝트 / 2020  
팀 프로젝트로 2인이 팀을 이루어서 진행하였다. TCP를 사용하여 다중 접속, 파일 전송, 연결 종료/재접속 알림 기능을 구현하였다.

### 7. 기본상식을 가진 페르소나 생성 / 팀 프로젝트 / 2020  
팀 프로젝트로 3인이 팀을 이루어서 진행하였다. 페르소나는 사용자의 특징 정보를 가진 고유의 정보로 타인에게 비추어지는 외적 성격을 나타낸다. CoMeT의 결과물을 문장 템플릿을 이용하여 문장화하였으며, GPT-2를 fine-tuning 하여 유사하고 다양한 페르소나를 생성하고, 정확하지 않은 생성 문장들은 NLI를 사용하여 필터링하였다.  

### 8. 네이버 영화 리뷰 분석 / 개인 프로젝트 / 2019  
개인 프로젝트로 데이터는 네이버 영화 리뷰를 사용하였으며, 전처리 과정에서 KoNLPy를 사용하여 형태소 분석, 불용어 제거등을 진행하였다. 출현 빈도가 높은 단어들은 word cloud로 시각화하였으며, 공기어들은 네트워크로 만들어 gephi를 사용하여 시각화하였다. 감성 분석으로는 0과 1로 라벨링된 리뷰들로 모델을 학습하여 사용하였다.  
