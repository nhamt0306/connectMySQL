import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class App {
  public static void main(String[] args) {
    System.out.println("--------------------");
    List<Category> list = findAll();
    System.out.println("Danh sách sản phẩm:");
    System.out.println("--------------------");
    for (Category c : list) {
      System.out.printf("%d - %s\n", c.getId(), c.getName());
    }
    System.out.println("--------------------");

    List<Category> list2 =  findCategory(3);
    for (Category c : list2) {
      System.out.printf("Sản phẩm tìm được là: %s\n", c.getName());
    }
  }

  public static List<Category> findAll() {
    Sql2o sql2o = new Sql2o("jdbc:mysql://localhost:3306/qlbh", "root", "");
    final String query = "select * from categories ";
    try (Connection con = sql2o.open()) {
      return con.createQuery(query)
        .executeAndFetch(Category.class);
    }
  }
  public static List<Category> findCategory(int catids) {
    Sql2o sql2o = new Sql2o("jdbc:mysql://localhost:3306/qlbh", "root", "");
    try (Connection con = sql2o.open()) {
      final String query = "select * from categories where CatID = :catid";
      return con.createQuery(query)
              .addParameter("catid", catids) /*Tham số thứ nhất của query, tham số truyền vào*/
              .executeAndFetch(Category.class);
    }
  }
}
