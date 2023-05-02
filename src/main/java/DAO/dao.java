package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.dto;

public class dao {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection
				("jdbc:oracle:thin:@//localhost:1521/xe", "ksol46", "0406");
		return con;
	}
	
	public String inquire (HttpServletRequest request, HttpServletResponse response) {
		ArrayList<dto> list = new ArrayList<dto>();
		
		try {
			conn = getConnection();
			String sql = "select"
					+ "	s.stid,"
					+ "	s.stname,"
					+ "	decode(substr(s.resident,8,1),1,'남',2,'여',3,'남',4,'여') as gender,"
					+ "	b.subject,"
					+ "	decode(b.classes,'01','전공필수','02', '전공선택', '03', '교양필수', '04', '교양선택') as class,"
					+ "	b.professor,"
					+ "	r.mid,"
					+ "	r.finall,"
					+ "	r.attend,"
					+ "	r.report,"
					+ "	r.etc,"
					+ "	to_char((r.mid*0.3)+(r.finall*0.3)+(r.attend*0.2)+(r.report*.1)+(r.etc*0.1),'fm00.0') as score,"
					+ " case"
					+ " when 90 <= to_char((r.mid*0.3)+(r.finall*0.3)+(r.attend*0.2)+(r.report*.1)+(r.etc*0.1),'fm00.0') then 'A'"
					+ " when 80 <= to_char((r.mid*0.3)+(r.finall*0.3)+(r.attend*0.2)+(r.report*.1)+(r.etc*0.1),'fm00.0') then 'B'"
					+ " when 70 <= to_char((r.mid*0.3)+(r.finall*0.3)+(r.attend*0.2)+(r.report*.1)+(r.etc*0.1),'fm00.0') then 'C'"
					+ " when 60 <= to_char((r.mid*0.3)+(r.finall*0.3)+(r.attend*0.2)+(r.report*.1)+(r.etc*0.1),'fm00.0') then 'D'"
					+ " when 60 > to_char((r.mid*0.3)+(r.finall*0.3)+(r.attend*0.2)+(r.report*.1)+(r.etc*0.1),'fm00.0') then 'F'"
					+ " end as grade"
					+ " from table_std_01 s"
					+ " join table_result_03 r"
					+ " on s.stid = r.stid"
					+ " join table_subject_02 b"
					+ " on b.subjectcode = r.dtcode"
					+ " order by b.subject DESC, s.stid asc";

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dto dto = new dto();
				
				dto.setStid(rs.getString(1));
				dto.setStname(rs.getString(2));
				dto.setGender(rs.getString(3));
				dto.setSubject(rs.getString(4));
				dto.setClasses(rs.getString(5));
				dto.setProfessor(rs.getString(6));
				dto.setMid(rs.getInt(7));
				dto.setFinall(rs.getInt(8));
				dto.setAttend(rs.getInt(9));
				dto.setReport(rs.getInt(10));
				dto.setEtc(rs.getInt(11));
				dto.setScore(rs.getString(12));
				dto.setGrade(rs.getString(13));
				
				list.add(dto);
			}
			request.setAttribute("list", list);
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "inquire.jsp";
	}
	
	public int insert (HttpServletRequest request, HttpServletResponse response) {
		String stid = request.getParameter("stid");
		String dtcode = request.getParameter("dtcode");
		int mid = Integer.parseInt(request.getParameter("mid"));
		int finall = Integer.parseInt(request.getParameter("finall"));
		int attend = Integer.parseInt(request.getParameter("attend"));
		int report = Integer.parseInt(request.getParameter("report"));
		int etc = Integer.parseInt(request.getParameter("etc"));
		int result = 0;
		
		try {
			conn = getConnection();
			String sql = "insert into table_result_03 values (?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, stid);
			ps.setString(2, dtcode);
			ps.setInt(3, mid);
			ps.setInt(4, finall);
			ps.setInt(5, attend);
			ps.setInt(6, report);
			ps.setInt(7, etc);
			
			result = ps.executeUpdate();
			
			conn.close();
			ps.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String statistics (HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<dto> slist = new ArrayList<dto>();
		
		try {
			
			conn = getConnection();
			String sql = "select"
					+ "	decode(s.course,'AD','전문학사','BD','학사','MD','석사','DD','박사'),"
					+ "	s.stid,"
					+ "	s.stname,"
					+ " count(r.dtcode) || '과목' as subcount,"
					+ " round((sum((r.mid*0.3)+(r.finall*0.3)+(r.attend*0.2)+(r.report*0.1)+(r.etc*0.1)))) as totalscore,"
					+ " round(sum(((r.mid*0.3)+(r.finall*0.3)+(r.attend*0.2)+(r.report*0.1)+(r.etc*0.1))) / count(r.dtcode),1) as average"
					+ " from table_std_01 s"
					+ " join table_result_03 r"
					+ " on s.stid = r.stid"
					+ " join table_subject_02 b"
					+ " on b.subjectcode = r.dtcode"
					+ " group by s.stid, decode(s.course,'AD','전문학사','BD','학사','MD','석사','DD','박사'), s.stname"
					+ " order by s.stid desc";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dto dto = new dto();
				
				dto.setCourse(rs.getString(1));
				dto.setStid(rs.getString(2));
				dto.setStname(rs.getString(3));
				dto.setSubcount(rs.getString(4));
				dto.setTotalscore(rs.getInt(5));
				dto.setAverage(rs.getString(6));
				
				slist.add(dto);
			}
			
			request.setAttribute("slist", slist);
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "statistics.jsp";
	}
}
