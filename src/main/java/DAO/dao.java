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
				dto.setScore(rs.getInt(12));
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
}
