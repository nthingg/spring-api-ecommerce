
# Spring API Ecommerce

Spring Boot API manages the e-commerce system, supporting functions such as product management, orders, users, shopping cart and payment. RESTful design, JWT security integration and relational database connectivity.
## Features

- Manage categories, items, users
- Authorization
- Stripe payment

## Tech Stack

**Client:** React, Vite

**Server:** Spring Boot, SQl Server


## Authors

- [@thinhnguyenngoc0104](https://github.com/thinhnguyenngoc0104)


## API Reference

#### Get all items

```http
  GET /api/v1.0/items
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Delete item

```http
  DELETE /api/v1.0/items/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to delete |

