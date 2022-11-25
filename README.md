# 관통_Final_PJT_EnjoyTrip
국내 관광지 정보와 여행 계획 및 여행지 정보를 공유하는 서비스입니다.

## 🧑‍🤝‍🧑 Team
| 조장 | 조원 |
| :--------: | :--------: |
| 이수민 | 박병우 |

## 💻 Skills
📌 Front-End : Vue.js / VueBootstrap   
📌 Back-End : Spring Boot / REST API  
📌 DB : MySQL / My Batis   
📌 LIBRARY : Vuex / FontAwesome / AOS (Animate On Scroll)     
📌 API : 한국관광공사 API / Kakao 지도 / OpenWeatherMap / Channel.io

## 🌉 Features

### 🏷️ Main
| **메인 페이지** | 
| :--------: | 
| <img src="https://user-images.githubusercontent.com/12388329/203815147-e99bd255-edff-4fad-86fe-5752327a5b69.png"  width="600px" height="300px"/>   | 
- 깔끔한 UI와 간단한 애니메이션으로 사용자 경험 향상시켰습니다
- 메인 페이지에는 사용자가 최대한 서비스를 많이 이용하도록 여러 여행지를 추천해줍니다

| **챗봇 기능** | **날씨 및 기능 네비게이션**    |
| :--------: | :--------: |
|  <img src="https://user-images.githubusercontent.com/12388329/203816133-a67db24a-6522-4cf7-bccb-929997e99af4.png"  width="300px" height="150px"/>  | <img src="https://user-images.githubusercontent.com/12388329/203816590-826f2839-b3f7-46fc-a959-0dcddc2e85a9.png"  width="300px" height="150px"/>  |
- 우측 하단에 사용자 편의성을 생각해서 챗봇으로 웹사이트에 대한 소개와 각 기능 사용법을 설명해주었습니다
- 오늘 및 일주일 간 기상 정보를 제공합니다
- 네비게이션 항목을 선택하면 같은 페이지 내 해당 기능으로 이동합니다

| **이달의 추천 여행지** |  **테마별 인기 여행지**    |
| :--------: | :--------: |
| <img src="https://user-images.githubusercontent.com/12388329/203816955-3bf55430-84e6-4998-b392-985c104a2551.png"  width="300px" height="150px"/>   | <img src="https://user-images.githubusercontent.com/12388329/203817578-59f85dcd-da2c-4e5a-8e96-bbe26f487233.png"  width="300px" height="150px"/>  |
- 매달 EnjoyTrip에서 가장 조회수가 많았던 여행지를 사용자에게 추천해줍니다
- 각 테마별로 제일 인기가 많은 여행지 정보를 제공합니다

| **인기 여행 계획** |  **핫 플레이스 자랑**    |
| :--------: | :--------: |
| <img src="https://user-images.githubusercontent.com/12388329/203820516-451dd616-8c4d-4e86-ad1a-59cbfd730474.png"  width="300px" height="150px"/>   | <img src="https://user-images.githubusercontent.com/12388329/203821055-ead0bc64-af32-4b6b-bd72-4015b54267b1.png"  width="300px" height="150px"/>  |
- 사용자가 작성한 여행 계획 중 조회수가 높은 몇 개의 여행계획을 추천합니다, 클릭 시 해당 게시글로 이동합니다
- 사용자들이 직접 업로드한 핫플레이스 이미지를 간단한 정보와 함께 제공합니다 

### 🏷️ 관광지 검색
| **시도, 구군 검색** | **검색 후 관광지 출력**    |
| :--------: | :--------: |
|  <img src="https://user-images.githubusercontent.com/12388329/203822656-a9e91cb7-627b-4857-9ecf-3473ae731a12.png"  width="300px" height="150px"/>  | <img src="https://user-images.githubusercontent.com/12388329/203822784-1b452ed1-32a4-4f9c-b45b-4a7e3cc6810a.png"  width="300px" height="150px"/>  |
- 시도 및 구군 별로 선택 후 해당 지역 내 관광지를 검색할 수 있습니다
- 검색 후 관광지를 테마별로 나눠서 확인하거나 클릭해서 상세정보를 확인할 수 있습니다

| **관광지 상세정보** | 
| :--------: |
|  <img src="https://user-images.githubusercontent.com/12388329/203822866-6b8ad4ff-7355-4da3-9d28-7cb5c87d2bf6.png"  width="300px" height="150px"/>  | 
- 관광지 정보와 상세 설명, 그리고 지도에서의 위치를 확인 가능합니다

### 🏷️ 여행계획 짜기
| **여행계획 짜기 화면** | **키워드 검색**    |
| :--------: | :--------: |
|  <img src="https://user-images.githubusercontent.com/12388329/203824568-e68aae16-0946-4c0e-8552-d4b22c78236f.png"  width="300px" height="150px"/>  | <img src="https://user-images.githubusercontent.com/12388329/203824661-86057f41-d557-4cec-bdf1-b181a04352f6.png"  width="300px" height="150px"/>  |
- 검색창으로 여행지 키워드 검색 후 계획에 추가할 수 있습니다
- 키워드 검색 후 여행지가 리스트와 지도에 마커로 표시됩니다 

| **여행지 추가** | **마커 숨기기/해제**    |
| :--------: | :--------: |
|  <img src="https://user-images.githubusercontent.com/12388329/203825106-18add087-81b9-4bd9-982d-4d7d9825a51f.png"  width="300px" height="150px"/>  | <img src="https://user-images.githubusercontent.com/12388329/203825251-afec9763-a483-4877-9483-8f7621c6e7d5.png"  width="300px" height="150px"/>  |
- 리스트 내 각 아이템을 클릭하면 여행지의 위치를 확인할 수 있습니다
- 리스트에 있는 여행지를 더블 클릭하거나 마커를 클릭하면 해당 여행지가 계획에 추가됩니다
- 계획에 추가된 여행지는 다른 색의 마커로 표시되고 밑에 공간에 따로 표시됩니다
- 지도 우측 하단의 버튼을 통해 다른 마커들을 숨기거나 표시할 수 있습니다

| **여행지 편집** | **여행계획 작성**    |
| :--------: | :--------: |
|  <img src="https://user-images.githubusercontent.com/12388329/203825337-a61b1c69-c9ab-4b2e-97bf-8b8d36a38586.png"  width="300px" height="150px"/>  | <img src="https://user-images.githubusercontent.com/12388329/203825643-897db41e-8a61-4e40-90d8-3b5b4c5eb749.png"  width="300px" height="150px"/>  |
- 계획에 추가된 여행지의 순서를 조정하거나 삭제할 수 있습니다
- 다음 화면에서 여행계획의 제목과 내용, 각 여행지에 대한 메모를 작성 할 수 있습니다

### 🏷️ 여행정보 공유
| **여행정보 공유 게시판** | **여행정보 공유 게시글**    |
| :--------: | :--------: |
|  <img src="https://user-images.githubusercontent.com/12388329/203827518-e2d98062-f4ed-4aa1-9ffe-9457cf0f0c17.png"  width="300px" height="150px"/>  | <img src="https://user-images.githubusercontent.com/12388329/203827631-d09fd42d-4881-4b50-a595-74ceb45ac0b3.png"  width="300px" height="150px"/>  |
- 사용자가 작성한 여행계획을 게시판에서 확인할 수 있습니다
- 클릭하면 각 게시글을 확인할 수 있으며, 본인이 작성한 게시글일 경우 수정 및 삭제가 가능합니다
- 게시글 내 여행지를 클릭하면 여행지 상세정보를 확인할 수 있습니다

### 🏷️ 핫 플레이스
| **핫 플레이스 게시판** | **핫 플레이스 공유**    |
| :--------: | :--------: |
|  <img src="https://user-images.githubusercontent.com/12388329/203828193-b0123bf7-d1db-4769-b2ed-4299fbb9347e.png"  width="300px" height="150px"/>  | <img src="https://user-images.githubusercontent.com/12388329/203828377-00a3dadc-7bd0-49fc-b8b7-135ccd636add.png"  width="300px" height="150px"/>  |
- 사용자가 공유하고 싶은 핫 플레이스를 이미지 게시판으로 보여줍니다
- 사용자가 직접 간단한 정보와 이미지 파일을 올려 핫 플레이스를 공유할 수 있습니다

| **핫 플레이스 확인** | 
| :--------: | 
|  <img src="https://user-images.githubusercontent.com/12388329/203828495-f4ddef4d-54b0-402d-82ff-310def6f4113.png"  width="300px" height="150px"/>  | 
- 각 사진에 마우스를 올리면 핫 플레이스 정보와 공유한 사용자를 확인할 수 있습니다
- 본인이 공유한 핫 플레이스는 수정과 삭제를 할 수 있습니다

### 🏷️ 내 프로필
| **프로필 확인** | **프로필 수정**    |
| :--------: | :--------: |
|  <img src="https://user-images.githubusercontent.com/12388329/203829794-272a9905-782a-4657-8fbf-accf19639aa7.png"  width="300px" height="150px"/>  | <img src="https://user-images.githubusercontent.com/12388329/203829887-d6a2c6e8-25a1-485f-b71e-ca1b5addc6a4.png"  width="300px" height="150px"/>  |
- 로그인 후 유저의 프로필을 한 눈에 확인할 수 있습니다
- 프로필 수정 및 회원 탈퇴를 할 수 있습니다
- 프로필 수정 시 파일을 업로드해 프로필 사진을 바꿀 수 있습니다

### 🏷️ 유저 관리

| **로그인** | **회원가입**    |
| :--------: | :--------: |
|  <img src="https://user-images.githubusercontent.com/12388329/203830455-7702435c-cdf8-44e4-81ca-ee95cd3b95b6.png"  width="300px" height="150px"/>  | <img src="https://user-images.githubusercontent.com/12388329/203830536-a18cd247-325a-45c7-abf5-ad07ae6a2070.png"  width="300px" height="150px"/>  |
- Access Token과 Random Token으로 로그인 처리 및 회원 정보를 저장합니다
- 회원가입 시 유효성 검사로 중복 아이디를 걸러줍니다


| **아이디 찾기** | **아이디 찾기 이메일**    |
| :--------: | :--------: |
|  <img src="https://user-images.githubusercontent.com/12388329/203830585-9cb84e1c-587f-4660-9052-342f254ddeaa.png"  width="300px" height="150px"/>  | <img src="https://user-images.githubusercontent.com/12388329/203830679-e3f58230-cffc-4d0a-add8-6e439bb5406f.png"  width="300px" height="150px"/>  |
- 이메일을 입력하면 해당 이메일로 등록된 유저 정보가 있는지 확인해줍니다
- 만약 회원이 존재한다면 해당 이메일로 아이디를 전송합니다


| **비밀번호 찾기** | **비밀번호 찾기 이메일**    |
| :--------: | :--------: |
|  <img src="https://user-images.githubusercontent.com/12388329/203830733-c89ce084-a3c1-4029-8b97-ee9ecff960ff.png"  width="300px" height="150px"/>  | <img src="https://user-images.githubusercontent.com/12388329/203830787-4b44408a-748f-459a-a8c8-13db54ac17d0.png"  width="300px" height="150px"/>  |
- 비밀번호 찾기는 아이디를 입력하면 유저 정보 확인 후 해당 유저의 이메일로 임시 비밀번호를 전송합니다
- 사용자는 임시 비밀번호로 로그인할 수 있으며, 회원정보 수정에서 비밀번호를 수정할 수 있습니다


## 🗄️ Structure

### 🏷️ DB Schema
![DBSchema](https://user-images.githubusercontent.com/12388329/203832348-8fc6f4b9-37d1-4b0a-ac9c-762c873af15d.png)

### 🏷️ Class Diagram
![ClassDiagram cld](https://user-images.githubusercontent.com/12388329/203832583-b4b27be3-32f1-4112-afec-7f9940b88ab5.jpg)

### 🏷️ Usecase Diagram
![usecase](https://user-images.githubusercontent.com/12388329/203877431-9e3e1378-54ce-4625-a2ab-ed7e737f2cc1.jpg)

