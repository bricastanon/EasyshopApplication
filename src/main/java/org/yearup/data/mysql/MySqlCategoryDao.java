package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao {

    public MySqlCategoryDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM categories"; // Adjust table name as per your database schema
        List<Category> categories = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("category_id")); // Replace "id" with the actual column name
                category.setName(resultSet.getString("name")); // Replace "name" with the actual column name
                // Map other columns if necessary
                categories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
            throw new RuntimeException("Error fetching categories", e);
        }

        return categories;
    }

    @Override
    public Category getById(int categoryId) {
    String sql = "SELECT * FROM categories WHERE category_id = ?";
    Category category = null;
    try (Connection connection = dataSource.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, categoryId);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                category = mapRow(resultSet);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Handle exceptions appropriately
         throw new RuntimeException("Error fetching category by id", e);
    }
    return category;
    }

    @Override
    public Category create(Category category) {
        String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        category.setCategoryId(generatedKeys.getInt(1)); // Set the generated ID
                    }
                }
            } else {
                throw new RuntimeException("Failed to insert category");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
            throw new RuntimeException("Error creating category", e);
        }

        return category;
    }

    @Override
    public void update(int categoryId, Category category) {
        String sql = "UPDATE categories SET name = ?, description = ? WHERE category_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setInt(3, categoryId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Failed to update category");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
             throw new RuntimeException("Error updating category", e);
        }
    }

    @Override
    public void delete(int categoryId) {
    String sql = "DELETE FROM categories WHERE category_id = ?";

    try (Connection connection = dataSource.getConnection();
    PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, categoryId);
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected == 0) { throw new RuntimeException("Failed to delete category");
        }
    } catch (SQLException e) {
         e.printStackTrace(); // Handle exceptions appropriately
         throw new RuntimeException("Error deleting category", e);
    }
}

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}
