package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Posts_Chapter07 {
	  public static void main(String[] args) {

	        Connection con = null;
	        Statement statement = null;

	        try {
	            // データベースに接続
	            con = DriverManager.getConnection(
	                "jdbc:mysql://localhost/challenge_java",
	                "root",
	                "Yuuki03Nyanmaru"
	            );

	            System.out.println("データベース接続成功：" + con);

	            // SQLクエリを準備
	            statement = con.createStatement();

	            // 追加する投稿データ
	            String[][] postList = {
	                {"1003","2023-02-08","昨日の夜は徹夜でした・・","13"},
	                {"1002","2023-02-08","お疲れ様です！","12"},
	                {"1003","2023-02-09","今日も頑張ります！","18"},
	                {"1001","2023-02-09","無理は禁物ですよ！","17"},
	                {"1002","2023-02-10","明日から連休ですね！","20"},
	            };

	            // 投稿データを追加
	            System.out.println("レコード追加を実行します");
	            int recordsAdded = 0;
	            for (String[] postData : postList) {
	                String userId = postData[0];
	                String postedAt = postData[1];
	                String postContent = postData[2];
	                String likes = postData[3];

	                String insertSql = "INSERT INTO posts (user_id, posted_at, post_content, likes) "
	                        + "VALUES ('" + userId + "', '" + postedAt + "', '" + postContent + "', '" + likes + "')";
	                statement.executeUpdate(insertSql);
	                recordsAdded++;
	            }

	            System.out.println(recordsAdded + "件のレコードが追加されました");

	            // ユーザーIDが1002の投稿を検索して出力
	            System.out.println("ユーザーIDが1002のレコードを検索しました");
	            ResultSet result = statement.executeQuery("SELECT * FROM posts WHERE user_id = 1002 OR user_id = 1003;");
	            int count = 1;
	            while (result.next()) {
	                String postedAt = result.getString("posted_at");
	                String postContent = result.getString("post_content");
	                String likes = result.getString("likes");
	                System.out.println(count + "件目：投稿日時=" + postedAt + "／投稿内容=" + postContent + "／いいね数=" + likes);
	                count++;
	            }

	        } catch(SQLException e) {
	            System.out.println("エラー発生：" + e.getMessage());
	        } finally {
	            // 使用したオブジェクトを解放
	            if( statement != null ) {
	                try { statement.close(); } catch(SQLException ignore) {}
	            }
	            if( con != null ) {
	                try { con.close(); } catch(SQLException ignore) {}
	            }            
	        }
	   }
}
