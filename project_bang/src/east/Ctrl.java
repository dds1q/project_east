package east;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;

import east.Util;

@Controller
public class Ctrl {
	private BangDAO BangDao = null;
	private UserDAO UserDao = null;
	private BoardDAO BoardDao = null;
	private CommentDAO CommentDao = null;
	private LikeDAO LikeDao = null;
	private FollowDAO FollowDao = null;
	private User_infoDAO User_infoDao = null;
	private MessageDAO MessageDao = null;
	public FollowDAO getFollowDao() {
		return FollowDao;
	}
	public MessageDAO getMessageDao() {
		return MessageDao;
	}
	public void setMessageDao(MessageDAO messageDao) {
		MessageDao = messageDao;
	}
	public User_infoDAO getUser_infoDao() {
		return User_infoDao;
	}
	public void setUser_infoDao(User_infoDAO user_infoDao) {
		User_infoDao = user_infoDao;
	}
	public void setFollowDao(FollowDAO followDao) {
		FollowDao = followDao;
	}
	public LikeDAO getLikeDao() {
		return LikeDao;
	}
	public void setLikeDao(LikeDAO likeDao) {
		LikeDao = likeDao;
	}
	public CommentDAO getCommentDao() {
		return CommentDao;
	}
	public void setCommentDao(CommentDAO commentDao) {
		CommentDao = commentDao;
	}
	public BoardDAO getBoardDao() {
		return BoardDao;
	}
	public void setBoardDao(BoardDAO boardDao) {
		BoardDao = boardDao;
	}

	public UserDAO getUserDao() {
		return UserDao;
	}
	public void setUserDao(UserDAO userDao) {
		UserDao = userDao;
	}
	public BangDAO getBangDao() {
		return BangDao;
	}
	public void setBangDao(BangDAO bangDao) {
		BangDao = bangDao;
	}		
	@RequestMapping("/login.east")
	public String login( HttpSession session ) throws Exception 
	{
		//	Ȥ�� ������ �α��� ������ ���ǿ� ���������� ������ �����ϴ� �ڵ�
		if( session.getAttribute("username") != null ) {
			session.removeAttribute("username");
		}
		return "login";
	}
	
	@RequestMapping("/login2.east")
	public String login2(@RequestParam(value="username",required=false) String username ,
		@RequestParam("password") String password , HttpSession session) throws Exception 
	{
		boolean t = UserDao.findUser( username , password );

		
		//	������ DB���� ���̵�� ��ȣ�� �˻��ؼ� �����ؾ� �ϴµ� ���⼭�� ���!!
		if( t )
		{				
			session.setAttribute( "username", username );			
			return "redirect:board_list.east";
		} 
		else 
		{			
			return "redirect:login.east?ecode=login_fail";
		}
	}
	
	@RequestMapping(value="/logout.east", method=RequestMethod.GET)
	public void logout( HttpServletRequest request,  HttpServletResponse response ) throws Exception 
	{
		HttpSession session = request.getSession();
		
		session.invalidate();		
		
		response.setContentType("text/html; charset=UTF-8");             
        PrintWriter out = response.getWriter();
       
        out.println("<script language='javascript'>alert('�α׾ƿ��Ǿ����ϴ�'); location.href='login.east';</script>");
        out.flush();
       
	}
	
	// ȸ�����Խ� CAPTCH �������ִ� �ڵ�
	@RequestMapping("/sign_up.east")
	public void sign_up( HttpServletRequest request, HttpServletResponse response ) throws Exception 
	{
		String clientId = "6yyle1am8h";//���ø����̼� Ŭ���̾�Ʈ ���̵�";
        //���ø����̼� Ŭ���̾�Ʈ ��ũ����";
        String clientSecret = "g6zVbghq5vnGFMVPbfi0kClYNRR4rYrZYNsSTnGJ";
        String key = null;
        String tempname = null;		//���ϸ�
        try {
            String code = "0"; // Ű �߱޽� 0,  ĸ�� �̹��� �񱳽� 1�� ����
            String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha/v1/nkey?code=" + code;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // ���� ȣ��
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // ���� �߻�
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer sb = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);	//{"key":"NGq18N0nGyObDES9"}	// Ű�� ����
            }
            br.close();

            // {"key":"NGq18N0nGyObDES9"} JSON ���Ͽ��� NGq18N0nGyObDES9�� ������ ��
            JSONObject jo = new JSONObject( sb.toString() );
            key = jo.getString("key");      
            
			
        } catch (Exception e) {
            System.out.println(e);
        } 
        try {            
            String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha-bin/v1/ncaptcha?key=" + key + "&X-NCP-APIGW-API-KEY-ID" + clientId;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // ���� ȣ��
                InputStream is = con.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
                // ������ �̸����� ���� ����
                tempname = Long.valueOf(new Date().getTime()).toString();
                File f = new File(Util.upload() + tempname + ".jpg");	//����� ���,���ϸ�
                f.createNewFile();
                OutputStream outputStream = new FileOutputStream(f);
                while ((read =is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                outputStream.close();
                is.close();
            } else {  // ���� �߻�
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer sb = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    sb.append(inputLine);
                }
                br.close();
                System.out.println(sb.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        request.setAttribute("key", key );
        request.setAttribute("fname", tempname );
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/sign_up.jsp");
        rd.forward( request , response );
		
	}

	// ȸ������ ( CAPTCH Ȯ�� )
	@RequestMapping("/sign_up2.east")
	public void adduser( @RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response) throws Exception 	{

		
		String clientId = "6yyle1am8h";//���ø����̼� Ŭ���̾�Ʈ ���̵�";
        String clientSecret = "g6zVbghq5vnGFMVPbfi0kClYNRR4rYrZYNsSTnGJ";//���ø����̼� Ŭ���̾�Ʈ ��ũ����";
        String key = request.getParameter("key"); // ĸ�� Ű �߱޽� ���� Ű��
        String captcha = request.getParameter("captcha"); // ����ڰ� �Է��� ĸ�� �̹��� ���ڰ�
        response.setContentType("text/html; charset=UTF-8");             
        PrintWriter out = response.getWriter();                
      
        System.out.println( key );
        System.out.println( captcha );
        Boolean result = false;
        try {
            String code = "1"; // Ű �߱޽� 0,  ĸ�� �̹��� �񱳽� 1�� ����            
            String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha/v1/nkey?code=" + code +"&key="+ key + "&value="+ captcha;
            
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // ���� ȣ��
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // ���� �߻�
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer sb = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            br.close();
            
            JSONObject jo = new JSONObject( sb.toString() );
            result = jo.getBoolean( "result" );
            System.out.println( result );            
        } catch (Exception e) {
            System.out.println(e);
        }
        if( result ) {
    		try {			
				UserDao.addUser( username , password );	
				User_infoDao.addUser_info( username );
    		}
    		catch( Exception e ) { 
    			if ( e.toString().indexOf("PRIMARY") != -1) {
    				out.println("<script language='javascript'>alert('�ߺ��� �����Դϴ�'); location.href='sign_up.east';</script>");
    				out.flush();
    				
    			}
    		}
        } else {
        	out.println("<script language='javascript'>alert('ĸ������'); location.href='sign_up.east';</script>");
        	out.flush();
        	
        }
        out.println("<script language='javascript'>alert('������ �Ϸ�Ǿ����ϴ�'); location.href='login.east';</script>");
        out.flush();		
	}
	
	//�Խñ� ��� ���( ���� ȭ�� )
	@RequestMapping("/board_list.east")
	public ModelAndView board_list( HttpSession session ) throws Exception{
		
		ModelAndView mnv = new ModelAndView();
		String username = (String)session.getAttribute("username");
		
		mnv.setViewName("board/board_list");				
		mnv.addObject("list", BoardDao.findAll());		
		mnv.addObject("info" , UserDao.User_info( username ) );
		mnv.addObject("following", FollowDao.findFollowing( username ));
		mnv.addObject("follower", FollowDao.findFollower( username ));		
		
		return mnv;
	}	
	//Ư�� ������ �Խñ� ��� ���
	@RequestMapping("/board_list_other.east")
	public ModelAndView board_list_other( @RequestParam("username") String username_other , HttpSession session) throws Exception{
		
		ModelAndView mnv = new ModelAndView();	
		String username_me = (String)session.getAttribute("username");

		mnv.setViewName("board/board_list_other");
		mnv.addObject("username_other" , username_other );	
		mnv.addObject("follow_exist" , FollowDao.findFollow( username_me, username_other));
		mnv.addObject("info" , UserDao.User_info( username_other ) );
		mnv.addObject("list", BoardDao.findAll());		
		mnv.addObject("following", FollowDao.findFollowing( username_other ));
		mnv.addObject("follower", FollowDao.findFollower( username_other ));
		mnv.addObject("me", UserDao.User_info( username_me ));
		
		return mnv;
	}
	//�ȷο��� ��� ����
	@RequestMapping("/followers.east")
	public ModelAndView followers( @RequestParam("to_user") String to_user) throws Exception{
		
		ModelAndView mnv = new ModelAndView();			

		mnv.setViewName("Follower");
		Map<String,String> userinfo = new HashMap<>();
		List<FollowVO> follow = FollowDao.findFollower( to_user );
		for( FollowVO vo : follow  ) {
			userinfo.put( User_infoDao.findUser_info( vo.getFrom_user() ).getFsn() , User_infoDao.findUser_info( vo.getFrom_user() ).getUser_nick());
			
		}
		mnv.addObject("userinfo", userinfo ); 
		return mnv;

	}	
	
	//�ȷ��׵� ��� ����
	@RequestMapping("/following.east")
	public ModelAndView following( @RequestParam("from_user") String from_user ) throws Exception{
		
		ModelAndView mnv = new ModelAndView();			

		mnv.setViewName("Following");
		Map<String,String> userinfo = new HashMap<>();
		List<FollowVO> follow = FollowDao.findFollowing( from_user );
		for( FollowVO vo : follow  ) {
			userinfo.put( User_infoDao.findUser_info( vo.getTo_user() ).getFsn() , User_infoDao.findUser_info( vo.getTo_user() ).getUser_nick());
			
		}
		mnv.addObject("userinfo", userinfo ); 
		return mnv;
	}
	@RequestMapping("/board_list_nick.east")
	public ModelAndView board_list_nick( @RequestParam("nick") String nick, HttpSession session) throws Exception{
		
		ModelAndView mnv = new ModelAndView();	
		String username_me = (String)session.getAttribute("username");
		String username_other = User_infoDao.getUsername( nick );

		mnv.setViewName("board/board_list_other");
		mnv.addObject("username_other" , username_other );	
		mnv.addObject("follow_exist" , FollowDao.findFollow( username_me, username_other));
		mnv.addObject("info" , User_infoDao.findUser_info( username_other ) );
		mnv.addObject("me" , User_infoDao.findUser_info( username_me ) );
		mnv.addObject("list", BoardDao.findAll());		
		mnv.addObject("following", FollowDao.findFollowing( username_other ));
		mnv.addObject("follower", FollowDao.findFollower( username_other ));
		
		return mnv;
	}

	
	// �Խñ� �ۼ� ȭ�� ����
	@RequestMapping("/board_write.east")
	public String board_write( HttpSession session ) throws Exception 
	{			
		return "board/board_write";
	}
	
	// �Խñ� ����
	@RequestMapping("/addBoard.east")
	public void addBoard( HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{		
		ModelAndView mnv = new ModelAndView();
		
		MultipartRequest mpr = new MultipartRequest( request , Util.upload(), 
				1024*1024*16 , "utf-8", null );
		
		String title = mpr.getParameter("title");
		if( title == null || title.equals("")) {
			mnv.setViewName("redirect:board_write.east?ecode=invalid_content");
			
		}
		
		String content = mpr.getParameter("content");
		if( content == null || content.equals("")) {
			mnv.setViewName("redirect:board_write.east?ecode=invalid_content");
			
		}
			
		Object author = session.getAttribute("username");
		if( author == null || author.equals("")) {
			mnv.setViewName("redirect:board_write.east?ecode=invalid_author");
			
		}		
		BoardVO vo = new BoardVO();
		String ofn = mpr.getOriginalFileName("board_image");
		if( ofn != null )
		{
			File file = mpr.getFile("board_image");
			
			String fsn = UUID.randomUUID().toString();
			file.renameTo( new File( Util.upload() + fsn + ".jpg" ) );

			vo.setOfn( ofn );
			vo.setFsn( fsn );
		}
		vo.setTitle( title );
		vo.setContent( content );
		vo.setAuthor( (String)author );			
		BoardDao.addBoard( vo );
		
		response.setContentType("text/html; charset=UTF-8");             
        PrintWriter out = response.getWriter();        
        out.println("<script>alert('�ۼ� �Ϸ�'); window.parent.location.href='board_list.east';</script>");
		
		
		
	}
	
	// �Խñ� ��ȸ�ϱ�( ������ȣ�� �۹�ȣ�� ����)
	@RequestMapping("/viewBoard.east")
	public ModelAndView getView( HttpSession session, @RequestParam("no") String no ) throws Exception{
		ModelAndView mnv = new ModelAndView();

		mnv.setViewName("board/viewBoard");		
		String username = (String)session.getAttribute("username");
		String author = BoardDao.findBoard( no ).getAuthor();
		mnv.addObject("like" , LikeDao.findLike(  no , username ) );
		mnv.addObject("board", BoardDao.findBoard( no ));		
		mnv.addObject("comment", CommentDao.findAll( no ));		
		mnv.addObject("info", UserDao.User_info( author ) );	

		return mnv;
	}
	// ���� �̹���, �г��� ã����
	@RequestMapping("/userimage.east")
	public void getUserimage( @RequestParam("no") String no , HttpServletResponse response) throws Exception{

		String author = BoardDao.findBoard( no ).getAuthor();		
		JSONObject jo = new JSONObject();
		
	    jo.put("success", true );
	    jo.put("user_image", UserDao.User_info( author ).getFsn());
	    jo.put("user_nick", UserDao.User_info( author ).getUser_nick());
		response.setContentType("text/html; charset=UTF-8");             
        PrintWriter out = response.getWriter();
	    out.print( jo );	
	}
	
	// �Խñ� Ǯ���� ��ȸ�ϱ�, ��� ���� ( ������ȣ�� �۹�ȣ�� ����)
	@RequestMapping("/viewBoard_full.east")
	public ModelAndView getView_full( HttpSession session, @RequestParam("no") String no ) throws Exception{
		ModelAndView mnv = new ModelAndView();

		mnv.setViewName("board/viewBoard_full");
		
		Object username = session.getAttribute("username");
		String author = BoardDao.findBoard( no ).getAuthor();
		
		BoardDao.updateviewCount( no );
		mnv.addObject("like" , LikeDao.findLike(  no , (String)username ) );
		mnv.addObject("board", BoardDao.findBoard( no ));		
		mnv.addObject("comment", CommentDao.findAll( no ));	
		mnv.addObject("info", UserDao.User_info( author ) );
		mnv.addObject("info2" , UserDao.User_info( (String)username ) );
		
		return mnv;
	}
	
	//�Խñ� �����ϴ� �������� �̵�
	@RequestMapping("/modifyPage.east")
	public ModelAndView modifyBoard_Page( @RequestParam("no") String no ) throws Exception{	
		
		ModelAndView mnv = new ModelAndView();

		mnv.setViewName("board/board_modf");
	
		mnv.addObject("vo", BoardDao.findBoard( no ));		
		
		return mnv;
	}	

	//�Խñ� ����	
	@RequestMapping(value="/modifyBoard.east", method= RequestMethod.POST )
	public void modifyBoard( HttpServletRequest request,HttpServletResponse response ) throws Exception{		
		
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        MultipartRequest mpr = new MultipartRequest( request , Util.upload(), 
				1024*1024*16 , "utf-8", null );
        
        BoardVO vo = new BoardVO();
        
		String no = mpr.getParameter("no");
		String title = mpr.getParameter("title");
		String content = mpr.getParameter("content");
		
		String ofn = mpr.getOriginalFileName("board_image");
		if( ofn != null )
		{
			File file = mpr.getFile("board_image");
			
			String fsn = UUID.randomUUID().toString();
			file.renameTo( new File( Util.upload() + fsn + ".jpg" ) );

			vo.setOfn( ofn );
			vo.setFsn( fsn );
		}	
		vo.setNo( Integer.parseInt( no ) );
		vo.setTitle( title );
		vo.setContent( content );
		
		if( title == null || title.equals("")) {
			out.println("<script>alert('������ �����ϴ�'); history.go(-1);</script>");
		}else if( content == null || content.equals("")) {
			out.println("<script>alert('������ �����ϴ�'); history.go(-1);</script>");
		}else {
			BoardDao.modifyBoard( vo );		
	        out.println("<script>alert('�����Ǿ����ϴ�'); window.parent.location.href='board_list.east';</script>");
		}		
	}
	
	//�Խñ� ����
	@RequestMapping("/delBoard.east")
	public void delBoard( @RequestParam("no") String no , HttpServletResponse response ) throws Exception{		
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

		if( no == null || no.equals("")) {
			out.println("<script>alert('�߸��� �����Դϴ�'); history.go(-1);</script>");
		}
		CommentDao.delAllComment( no );
		BoardDao.delBoard( no );
		out.println("<script>alert('�����Ǿ����ϴ�'); window.parent.location.href='board_list.east';</script>");
	}	
	//��� ����
	@RequestMapping("/addComment.east")
	public ModelAndView addComment( HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{		
		ModelAndView mnv = new ModelAndView();
		
		String no = request.getParameter("no");	
		
		String content = request.getParameter("content");
		if( content == null || content.equals("")) {
			mnv.setViewName("redirect:viewBoard_full.east?no="+ no + "&ecode=invalid_content");
			return mnv;
		}
			
		Object author = session.getAttribute("username");
		if( author == null || author.equals("")) {
			mnv.setViewName("redirect:viewBoard_full.east?no="+ no + "&ecode=invalid_username");
			return mnv;
		}
		
		CommentVO vo = new CommentVO();		
		vo.setNo( Integer.parseInt( no ) );
		vo.setContent( content );
		vo.setAuthor( (String)author );			
		
		CommentDao.addComment( vo );			
		mnv.setViewName("redirect:viewBoard_full.east?no=" + no );
		
		return mnv;		
	}	
	//��� ����
	@RequestMapping("/delComment.east")
	public String delComment( @RequestParam("rno") String rno , @RequestParam("no") String no  ) throws Exception{		
		if( rno == null || rno.equals("")) {
			return "redirect:viewBoard_full.east?no="+ no + "ecode=invalid_rno";
		}
		CommentDao.delComment( rno , no );		
		return "redirect:viewBoard_full.east?no="+ no ;
	}	
	// ��� ����
	@RequestMapping(value="/modifyComment.east", method= RequestMethod.POST )
	public String modifyComment( HttpServletRequest request ) throws Exception{		
		
		String no = request.getParameter("no");
		String rno = request.getParameter("rno");
		String content = request.getParameter("content");
		
		if( content == null || content.equals("")) {
			return "redirect:modifyPage.east?ecode=invalid_content";
		}
		CommentDao.modifyComment( no , rno , content );
		
		return "redirect:viewBoard_full.east?no="+ no;
	}	
	//�޼��� ���� â���� �̵�
	@RequestMapping("/viewMessage.east")
	public ModelAndView modifyBoard_Page( HttpSession session , @RequestParam("username_other") String username_other ) throws Exception{	
		
		ModelAndView mnv = new ModelAndView();		
		mnv.setViewName("viewMessage");
	
		mnv.addObject("message", MessageDao.findAll());	
		mnv.addObject("username_other" , username_other) ;
		mnv.addObject("info", User_infoDao.findUser_info( username_other ));
	
		return mnv;
	}	
	
	//�޼��� �ۼ�
	@RequestMapping("/sendMessage.east")
	public ModelAndView sendMessage( HttpServletRequest request,
		HttpServletResponse response, HttpSession session ) throws Exception 
	{		
		MultipartRequest mpr = new MultipartRequest( request , Util.upload(), 
			1024*1024*16 , "utf-8", null );		
		ModelAndView mnv = new ModelAndView();
		
		String from_user = (String)session.getAttribute("username");
		String to_user = mpr.getParameter("to_user");
		String message = mpr.getParameter("message");	
		
		MessageVO vo = new MessageVO();
		vo.setMessage( message );
		vo.setFrom_user( from_user );
		vo.setTo_user( to_user );
			
//		String ofn = mpr.getOriginalFileName("apple");
//		if( ofn != null )
//		{
//			File file = mpr.getFile("apple");
//			
//			String fsn = UUID.randomUUID().toString();
//			file.renameTo( new File( Util.upload() + fsn ) );
//
//			vo.setOfn( ofn );
//			vo.setFsn( fsn );
//		}
		MessageDao.sendMessage( vo );			
		mnv.setViewName("redirect:viewMessage.east?username_other="+ to_user);
		
		return mnv;
	}
	
	//���� ��� ���	
	@RequestMapping("/list.east")
	public ModelAndView bang_list( HttpSession session ) throws Exception{
				
		ModelAndView mnv = new ModelAndView();
		if( session.getAttribute("username").equals("admin") ) {
			mnv.setViewName("bang_list_admin");
		} else {
			mnv.setViewName("bang_list");
		}		
		mnv.addObject("list", BangDao.findAll());		
		
		return mnv;
	}
		
	// ���� �ۼ�	( ���� ���ε� ��ɵ� ���� )
	@RequestMapping("/add.east")
	public ModelAndView add( HttpServletRequest request,
		HttpServletResponse response, HttpSession session ) throws Exception 
	{
		
		MultipartRequest mpr = new MultipartRequest( request , Util.upload(), 
			1024*1024*16 , "utf-8", null );
		
		ModelAndView mnv = new ModelAndView();
		
		String content = mpr.getParameter("content");
		if( content == null || content.equals("")) {
			mnv.setViewName("redirect:list.east?ecode=invalid_content");
			return mnv;
		}
			
		Object author = session.getAttribute("username");
		if( author == null || author.equals("")) {
			mnv.setViewName("redirect:list.east?ecode=invalid_author");
			return mnv;
		}
		
		BangVO vo = new BangVO();
		vo.setContent( content );
		vo.setAuthor( (String)author );
			
		String ofn = mpr.getOriginalFileName("apple");
		if( ofn != null )
		{
			File file = mpr.getFile("apple");
			
			String fsn = UUID.randomUUID().toString();
			file.renameTo( new File( Util.upload() + fsn ) );

			vo.setOfn( ofn );
			vo.setFsn( fsn );
		}
		BangDao.add( vo );			
		mnv.setViewName("redirect:list.east");
		
		return mnv;
	}		
	
	// ���� ����
	@RequestMapping("/del.east")
	public String del( @RequestParam("no") String no  ) throws Exception{		

		if( no == null || no.equals("")) {
			return "redirect:list.east?ecode=invalid_no";
		}
		BangDao.del( no );
		
		return "redirect:list.east";
	}
	
	//�� ��Ʈ Ŭ���� ���ƿ� ����..
	@RequestMapping("/addLike.east")
	public void addLike( HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{		

		String no = request.getParameter("no");		
		
		response.setContentType("text/html; charset=UTF-8");             
        PrintWriter out = response.getWriter();
       
					
		Object username = session.getAttribute("username");
		if( username == null || username.equals("")) {	       
			out.println( "<script language='javascript'>alert('�α׾ƿ��Ǿ����ϴ�'); location.href='login.east';</script>" );
		}
		
		LikeVO vo = new LikeVO();	
		
		vo.setNo( Integer.parseInt( no ) );		
		vo.setUsername( (String)username );		
		
		LikeDao.addLike( vo );	
		LikeDao.LikeCount( no );
		BoardVO vo1 = BoardDao.findBoard( no );
		
	    JSONObject jo = new JSONObject();
	    jo.put("success",true);
	    jo.put("likeCount", vo1.getLikeCount());
		
	    out.print( jo );
	}
	
	// ���ƿ� ��� 
	@RequestMapping("/delLike.east")
	public void delLike( HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{		
		
		String no = request.getParameter("no");		
		response.setContentType("text/html; charset=UTF-8");             
        PrintWriter out = response.getWriter();			
        
		Object username = session.getAttribute("username");
		if( username == null || username.equals("")) {
			out.println("<script language='javascript'>alert('�α׾ƿ��Ǿ����ϴ�'); location.href='login.east';</script>");
		}
		
		LikeVO vo = new LikeVO();		
		vo.setNo( Integer.parseInt( no ) );		
		vo.setUsername( (String)username );				
		
		LikeDao.delLike( vo );	
		LikeDao.LikeCount( no );
		
		BoardVO vo1 = BoardDao.findBoard( no );
		
	    JSONObject jo = new JSONObject();
	    jo.put("success",true);
	    jo.put("likeCount", vo1.getLikeCount() );
		
		
	    out.print( jo );
	}
	
	// ���ƿ� ã��
	@RequestMapping("/findLike.east")
	public void findLike( HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{		

		String no = request.getParameter("no");		
		
		response.setContentType("text/html; charset=UTF-8");             
        PrintWriter out = response.getWriter();
       
					
		Object username = session.getAttribute("username");
		if( username == null || username.equals("")) {	       
			out.println( "<script language='javascript'>alert('�α׾ƿ��Ǿ����ϴ�'); location.href='login.east';</script>" );
		}
		
		boolean result = LikeDao.findLikeA( no , (String)username );

		
	    JSONObject jo = new JSONObject();
	    jo.put("success",result);
		
	    out.print( jo );
	}
	
	// �ȷο�
	@RequestMapping("/addFollow.east")
	public void addFollow( HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{		

		String username_other = request.getParameter("username_other");		
		
		response.setContentType("text/html; charset=UTF-8");             
        PrintWriter out = response.getWriter();
       
					
		Object username = session.getAttribute("username");
		if( username == null || username.equals("")) {	       
			out.println( "<script language='javascript'>alert('�α׾ƿ��Ǿ����ϴ�'); location.href='login.east';</script>" );
		}
		
		FollowVO vo = new FollowVO();	
		
		vo.setFrom_user( (String)username );		
		vo.setTo_user( username_other );		
		
		FollowDao.addFollow( vo );	

	    JSONObject jo = new JSONObject();
	    jo.put("success",true);

		
	    out.print( jo );
	}

	// ���ȷο�
	@RequestMapping("/delFollow.east")
	public void delFollow( HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{		

		String username_other = request.getParameter("username_other");				
		response.setContentType("text/html; charset=UTF-8");             
        PrintWriter out = response.getWriter();       
					
		Object username = session.getAttribute("username");
		if( username == null || username.equals("")) {	       
			out.println( "<script language='javascript'>alert('�α׾ƿ��Ǿ����ϴ�'); location.href='login.east';</script>" );
		}
		
		FollowVO vo = new FollowVO();	
		
		vo.setFrom_user( (String)username );		
		vo.setTo_user( username_other );		
		
		FollowDao.delFollow( vo );	

	    JSONObject jo = new JSONObject();
	    jo.put("success",true);
		
	    out.print( jo );
	}
	
	// �����Ұ� ������������ �̵�
	@RequestMapping("/modifyInfoPage.east" )
	public ModelAndView modifyInfoPage( HttpSession session ) throws Exception{		
		ModelAndView mnv = new ModelAndView();
		String username = (String)session.getAttribute("username");
		mnv.setViewName("modifyInfo");		
		mnv.addObject("info" , UserDao.User_info( username ) );		
		return mnv;
	}
	
	// �����Ұ� ����
	@RequestMapping("/modifyInfo.east" )
	public void modifyInfo( HttpServletRequest request , HttpServletResponse response,
			HttpSession session  ) throws Exception{		
		ModelAndView mnv = new ModelAndView();
		MultipartRequest mpr = new MultipartRequest( request , Util.upload(), 
				1024*1024*16 , "utf-8", null );	
		
		String nick = mpr.getParameter("nick");
		if( nick == null || nick.equals("")) {
			mnv.setViewName("redirect:modifyInfo.east?ecode=invalid_nick");			
		}
		String intro = mpr.getParameter("intro");
		String username = (String)session.getAttribute("username");
		
		
		UserVO vo = new UserVO();
		String ofn = mpr.getOriginalFileName("image");
		if( ofn != null )
		{
			File file = mpr.getFile("image");
			
			String fsn = UUID.randomUUID().toString();
			file.renameTo( new File( Util.upload() + fsn + ".jpg" ) );
			vo.setOfn( ofn );
			vo.setFsn( fsn );
		}	
		vo.setUser_nick( nick );
		vo.setUser_intro( intro );
		vo.setUsername( username );
		
		UserDao.modifyInfo( vo );	
		
		response.setContentType("text/html; charset=UTF-8");             
        PrintWriter out = response.getWriter();
       
        out.println("<script language='javascript'>alert('���� �Ϸ�'); window.parent.location.href='board_list.east';</script>");
        out.flush();
	
		
		
		
	}	
	// ���� �ٿ�ε� 
	@RequestMapping("down.east")
	public ModelAndView down(HttpServletRequest request, HttpServletResponse response) throws Exception	{
		String fsn = request.getParameter("fsn");
		if( fsn == null ) {
			fsn = "1.png";
		}
		
		InputStream in = new FileInputStream( Util.upload() + fsn + ".jpg" );
		
		response.setContentType("application/octet");
		
		String ofn = request.getParameter("ofn");
		response.setHeader("content-disposition", "attachment;filename=" + ofn);
		
		OutputStream out = response.getOutputStream();
		int len = 0;
		byte[] buf = new byte[1024*4];
		
		while( ( len = in.read( buf ) ) != -1 ) {
			out.write(buf , 0 , len);
			out.flush();
		}
		
		out.close();
		in.close();
		
		return null;
	}	
}

	

