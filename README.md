# 메디닥

## 👩‍🏫PROJECT 소개

**기획의도**

코로나 팬데믹 상황으로 대면으로 예약하고 진료 대기 시간이 길어 기다리기 힘들어하는 사람들과 시간이 없는 사람들을 위해 집에서 간편하게 진료과, 병원 별로 간편하게 병원을 알아보고 앉은 자리에서 바로 병원을 예약할 수 있는 웹 사이트가 필요하다고 느껴 개발하게 되었다.

**주요기능**

병원명, 진료과, 증상별로 병원 검색 기능

병원 검색 결과는 현재 내 위치를 기준으로 가까운 곳부터 나오게 정렬되어 출력된다.

결과 표에서 병원명, 전화번호, 진료과, 현재 진료여부, 예약버튼을 확인 가능하다.

표에서 병원을 클릭할 때마다 그 병원의 위치가 지도에 마커로 표시된다.

표시된 마커를 클릭 시 병원의 세부정보와 코로나 위험지수 API를 이용해 병원이 있는 지역의 코로나 확진자 수를 확인할 수 있다.

병원 사용자는 표에서 환자를 관리할 수 있다.

1. 비회원 :   회원가입, 로그인,  서비스 소개, 이용안내
2. 일반 사용자 :  회원가입, 로그인, 병원 검색 및 예약,  서비스 소개, 이용안내, 마이페이지
3. 병원 사용자 :  회원가입, 로그인, 병원 예약 관리

![image](https://user-images.githubusercontent.com/111633448/207910373-276d9c59-ab0b-4ab4-8033-da48647b87e8.png)
* 페이지 권한 구성도
![image](https://user-images.githubusercontent.com/111633448/207907969-aa2b631d-40b0-4a23-a721-74ed20f206af.png)
* 아키텍처 구성도

🗓️ **작업기간** : 2022.3.10 ~ 2022.11.23

👨‍💻 **투입인원** : 2명

📒 **주요업무** 

- 기획 및 디자인 - 전반적인 레이아웃과 웹 디자인 기획 및 구현
- DB 설계 및 구축- 병원회원, 환자회원, 예약, 병원, 병원진료과 DB를 설계 및 구축
- 공공 API 연동 - 국립중앙의료원_전국 병·의원 찾기 서비스 API와 다음 우편 번호 API, 코로나 확진자 현황API를 JS와 Java를 통해 연동
- JS 로직 작성(공공 데이터 API 이용)
- JSP를 이용해 페이지 구축- JSP를 이용해 백엔드 로직 구현(로그인, 로그아웃, 검색)
- AWS lambda, step function, Amazon EventBridge를 이용해 일주일마다 공공데이터API를 호출하여 데이터를 추출, 변환, 적재하는 ETL 데이터 파이프라인 구성
- 병원 검색 속도 최적화(공공데이터api를 호출 하는 방식에서 DB에 병원 정보를 저장해두고 필요할때 DB에서 가져오는 방법으로 최적화하여 평균 2배정도 검색속도 향상)

🌱 **스킬 및 사용툴**

<img src="https://img.shields.io/badge/-html-E34F26?style=flat&logo=HTML5&logoColor=white"/> <img src="https://img.shields.io/badge/-css-1572B6?style=flat&logo=CSS3&logoColor=white"/> <img src="https://img.shields.io/badge/-JavaScript-F7DF1E?style=flat&logo=JavaScript&logoColor=white"/>
<img src="https://img.shields.io/badge/-jQuery-0769AD?style=flat&logo=jQuery&logoColor=white"/>
<img src="https://img.shields.io/badge/-Eclipse-2C2255?style=flat&logo=Eclipse IDE&logoColor=white"/>
<img src="https://img.shields.io/badge/-JSP-blue?style=flat&logoColor=white"/>
<img src="https://img.shields.io/badge/-aws-232F3E?style=flat&logo=Amazon AWS&logoColor=white"/>
<img src="https://img.shields.io/badge/-MySQL-4479A1?style=flat&logo=MySQL&logoColor=white"/>

✉️ **아쉬운 점 및 발전 방안**
* MVC 모델 1 패턴을 사용하여 아키텍처를 구성하였기 때문에 개발이 상대적으로 쉽고 개발속도가 빨랐지만 확장을 염두해두고 개발했음에도 불구하고 확장성과 유지보수성에 있어 불편함이 느껴져서 MVC 모델 2 패턴을 적용하는 방안을 생각 중 이다.
* AWS를 이용한 etl 데이터 파이프라인이 작동해 DB에 있는 데이터를 delete하고 insert하는 작업으로 인해 작동시간동안 DB를 이용하지 못하는 문제가 있다.
* AWS의 다른 기능들을 이용해 여러 기능을 만들어보고 싶었으나 AWS를 사용하기로 한 시기가 너무 늦어 AWS lambda, step function, Amazon EventBridge만 사용하였다.
* 병원 검색 기능의 로직 중 병원마다 DB에 접속해서 값을 가져오는 방법을 사용하는 로직이 있는데 이로인해 검색되는 병원이 너무 많아지는 경우엔 속도가 저하되는 문제가 발생한다.

## 🖌️UI 및 세부 기능 이미지 
![image](https://user-images.githubusercontent.com/111633448/207901347-943c41bf-71f1-4436-89d5-0c5714e2eb65.png)
* 예약 및 인덱스 페이지
![image](https://user-images.githubusercontent.com/111633448/207902451-0a8ec1b6-ee75-4018-a52e-24b67b98aff0.png)
* 병원 로그인 페이지
![image](https://user-images.githubusercontent.com/111633448/207909744-07465716-cb5d-4c13-9bcd-a58569cd993d.png)
* 병원명 검색(현재 위치에서 제일 가까운 위치부터 정렬되어 출력)
![image](https://user-images.githubusercontent.com/111633448/207909973-c4adbbbe-067c-4579-8a78-c19f68b9cf9c.png)
* 진료과 검색(현재 위치에서 제일 가까운 위치부터 정렬되어 출력)
![image](https://user-images.githubusercontent.com/111633448/207909994-99456c17-66f3-402d-96c7-156652af0daa.png)
* 예약 기능
![image](https://user-images.githubusercontent.com/111633448/207901848-314c9b79-2981-42d2-8043-d82bad891ed9.png)
* 예약 확인 및 마이페이지
![image](https://user-images.githubusercontent.com/111633448/207901943-ed275c04-433a-47e5-9e56-34a79c5c0f0a.png)
* 비밀번호 확인 페이지
![image](https://user-images.githubusercontent.com/111633448/207902019-9ff146d2-0622-47bb-bac0-f8aaeb1a1e78.png)
* 개인정보 관리 페이지
![image](https://user-images.githubusercontent.com/111633448/207902358-774022b6-b3c7-4789-b79a-ad28a68e10dd.png)
* 병원 예약 현황 페이지
