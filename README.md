# project_east

- Controll, VO, DAO 파일 : project_bang > src > east

- jsp 파일 : project_bang > WebContent > WEB-INF > jsp


1월 27일
>메인 페이지에 접근하기위해서는 아이디, 비밀번호를 입력해야 함
아이디, 비밀번호를 저장한 DB를 따로 생성하였고 DB에 존재하는 아이디, 비밀번호만 로그인 가능하도록 함
DB에 아이디 비밀번호를 생성하기 위한 회원가입 기능을 추가하였음. 이 때 회원가입 시 자동가입프로그램 방지기능을 위한 CAPTCHA 기능 설정함.
방명록 작성 페이지에서 로그아웃 할 수 있는 기능 추가. 로그아웃시 로그인 페이지로 넘어가며 뒤로가기를 눌러도 방명록 페이지로 접근할 수 없게 함.

1월 29일 

>관리자 전용 페이지와 일반유저용 페이지로 따로 나눴음 일단 관리자 전용페이지에는 전용 기능으로 게시글 삭제기능 추가함.
게시판 기능 추가!! 
게시판 글 등록, 수정, 삭제기능 추가함. 게시판 메인에는 작성자와 글 제목이 보이며 글 제목을 클릭하면 글 내용이 보이는 페이지로 넘어가도록 함\
다음목표 : 게시글에 댓글 기능 추가하기 
( 관리자 전용 페이지 기능은 시간 관계상 완성하지 못하였음.. )

1월 30일 

> 댓글기능 추가!!
 게시판 글 조회를 누르면 게시판 글에 달린 댓글을 확인할 수 있고 댓글 입력도 가능하다
 댓글 수정 , 삭제 기능 추가.  
 이때 댓글이 있는 게시판을 삭제할려고 하면 에러가 발생하였음. 댓글의 foreign키로 게시글의 no를 참조함으로써 바라보고 있는 댓글 테이블이존재하기 때문.
 그래서 게시판 삭제버튼을 누르면 먼저 게시판을 참조하고 있는 댓글들을 모두 삭제한 후 ( delAllComment ) 그 후에 게시판 삭제( delBoard ) 가 이루어지도록 함으로써 해결.
 
 2월1일
 > 댓글 수정기능을 조금 손봄
 > 수정하기 버튼을 누르면 수정할 수 있는 form이 댓글아래에 보여지는 형식으로 바뀜
 > 조회수 증가 기능 추가. 게시글 목록의 게시글을 클릭하여 조회할 때마다 조회수 1씩 증가하도록 함
 > 좋아요 기능 추가중 .. 좋아요 관련 테이블 생성

2월 2일
> 좋아요 기능 추가!!
> 게시글을 조회하면 좋아요 버튼이 있고 버튼을 누르면 좋아요 1이 올라간다, 좋아요 버튼은 좋아요취소 버튼으로 바뀌게 했다. 좋아요 취소 버튼을 누르면 좋아요가 1만큼 내려간다. 좋아요 데이터를 모아놓은 테이블을 생성하고( tbl_like ) 그에 따라 VO , DTO 도 생성하였음.
> 버튼을 누르면 뒤로가기와 동시에 새로고침을 하는 기능을 적용시키는 방법을 습득함. 나머지 다른 코드도 적용할 수 있으면 적용할 예정임.
> 써블릿에서 alert 가 발생하도록 코드를 짜고있으나 매번 PrintWriter를 선언해야하는 번거로움이 있음.. 다른 방법을 모색해보려고 한다.
> 다음 목표는 MYPAGE를 만들어 개인 정보를 저장할 수 있도록 한다. 더 나아가 친구추가 및 DM 기능을 추가해보려고 한다.
 
2월 3일
> 좋아요 기능을 수정함. 기존에는 좋아요 버튼을 누른 후 servlet 통신으로 인해 자동으로 페이지 이동이 되었다. 이때 좋아요 버튼을 누른 뒤 뒤로가기를 누르면 좋아요 버튼을 누르기 전 상태의 페이지로 되돌아가게 된다. 하지만 Like 테이블에는 이미 좋아요가 제대로 추가가 됨. 그래서 다시 그 페이지에서 좋아요를 한번 더 누르면 Like테이블에 좋아요가 두번 들어가므로 에러가 발생하게된다.
> 이를 방지하기 위해 페이지의 이동 없이 servlet 통신이 이루어져야 한다. 이를 위해서 AJAX 방식으로 하기로 결정함. AJAX 방식으로 수정 후 제대로 작동하는 것을 확인함.

2월 4일
> 전체적인 게시판 모습을 SNS 형태처럼 변경함. 기존에는 로그인 할 시 나오는 메인 화면에 제목들만 나열된 상황에서 제목을 클릭하면 페이지가 넘어가면서 제목에 해당하는 내용들이
> 펼쳐지는 전형적인 게시판의 형태였다면  변경후에는 메인화면에 게시물의 제목과 내용이 한번에 보이도록 설정함. 여기서 iframe을 이용하여 viewBoard에 연결한 후 board_list.jsp에서 반복문을
> 이용하여 viewBoard가 여러개 한번에 출력되도록 설정함. 다음 목표는 MYPAGE의 기능을 제대로 설정하여 본인의 정보를 기록할 수 있도록 한다.

2월 5일
> 상대방의 게시글이나 댓글에 아이디를 클릭하면 상대방의 메인페이지로 넘어가도록 설정함. 이 때 iframe 내에서 발생하는 동작이라 부모페이지가 넘어가야 하는 상황을 
발생시키기 위해 javascript에서 window.parent.location.href 를 이용하였음. 상대방의 메인페이지에는 상대방이 올린 게시글만 보이도록 설정했으며 여기에 팔로우 버튼과 DM 버튼을 설정하였음. 팔로우 기능을 추가할 예정.

2월 6일
> 팔로우 관련 테이블을 설정함, FollowVO, DTO 를 생성하였고 spring 파일 수정

2월 7일 
> 팔로우 기능 추가! 상대방의 페이지로 들어갔을 때 상대방에게 팔로잉 하지 않은 상태라면 메뉴에 팔로우 버튼이 생성된다. 팔로우 버튼을 누르면 follow 테이블에 
> from_user : 자신 / to_user : 상대방의 아이디로 기록되면서 팔로우 버튼이 언팔로우 버튼으로 바뀐다. 언팔로우 버튼을 누르면 테이블에서 삭제된다. 
> 게시글을 클릭하면 게시글의 모든 내용과 사진, 그리고 댓글 , 좋아요 기능들이 들어있는 모달창 페이지가 띄워지도록 하였음
> 유저의 자기소개 ( user_info ) 를 기록할 수 있도록 테이블을 생성함 ( User_infoVO , DTO 생성 ) 개인 메인페이지에 회원에 대한 소개가 간략하게 표시되도록 한다.

2월 8일
> 유저의 info를 작성하고 수정할 수 있도록 함. info 에는 유저의 닉네임 , 자기소개를 작성할 수 있도록 설정함. 차후에 info에 들어갈 내용들을 더 추가할 예정임
> 유저의 메인화면으로 들어가면 유저의 info가 페이지에 뜨도록 설정하였음. 
> DM기능을 간단하게 제작해보았음. 메세지들을 저장해놓은 DB를 생성함( MessageVO / DAO 생성 ).일단 DM 버튼은 상대방의 페이지로 이동했을 때 메인페이지의 팔로우 버튼 옆에 DM보내기 버튼을 생성하였다. DM보내기 버튼을 누르면
> 상대방과 내가 서로 교환한 메세지들이 보여진다.( 보통의 SNS 메세지전송 페이지처럼 내가 보낸 메세지는 오른쪽에, 상대방이 보낸 메세지는 왼쪽에 출력되도록 )
> 그리고 아래에는 메세지를 입력하고 보낼 수 있도록 해놓았음. 지금은 DM보내기 페이지로 이동하지만 이를 모달창 페이지가 띄워지도록 변경할 예정임. 그리고 메세지전송시
> 업로드도 할 수 있도록 

2월 9일
>본격적으로 전체적인 사이트 디자인을 꾸미기 시작함. 먼저 로그인 페이지와 메인페이지의 구색을 맞춰놓음
>내일안에 모든 페이지의 디자인을 수정하는 것이 목표임. 
>userinfo테이블이 굳이 필요한가? 라는 생각이 들었다. user테이블을 거쳐 userinfo 테이블로 접근하는 방식은 너무 비효율적이었다.( 코드를 짜면서 깨닫게됨..)
>userinfo테이블을 제거하고 user테이블( user_list )에 유저의 정보를 넣을 수 있도록 user_nick , profile_ofn , profile_fsn , user_info 필드를 새로 
>삽입하였다. 테이블 수정이 이루어졌기 때문에 전체적으로 코드를 다시 손봐야했다... 시간이 오래걸렸지만 이 작업을 해놓음으로써 다음에 코드를 짤 때
>복잡해지지 않게 될 것이다.


2월 10일
> 버그 수정하는데 예상보다 시간이 많이 걸려서 페이지 디자인은 얼마 못했음.. 모달창을 부트스트랩을 이용하여 적용해보았다. iframe 부분을 클릭하면 
> 모달창이 띄워지도록 해야하는데 그러기 위해서는 iframe의 id값을 매개변수로 하여 jquery로 모달창을 클릭시 반응하는 함수를 짜야 했는데
> jquery의 선택자로 iframe의 id를 매개변수로 그냥 넣으면 실행되지 않았다. 처음에는 $("#board" + index ).click( function(){}); 이런식으로 시도했는데
> 전혀 동작하지 않았고, 확인해보니 $("#board" + index ).contents().click(function(event){}); > iframe의 경우에는 이런식으로  .contents()를 붙여야
> 가능하다.
> 내일 목표는 모든 페이지를 bootstrap을 적용시켜 디자인을 구현할 예정이다..

2월 13일
> 페이지 디자인을 거의 다 완성시킴. 내일목표는 디자인 마감을 끝내는 것이다.



