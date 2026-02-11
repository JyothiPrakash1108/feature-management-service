📌 Feature Management Service API

🌍 Base URL  
https://feature-management-service-4.onrender.com

---

## ⚠️ Important Notice

This project is deployed on Render Free Tier.

- The application may go to sleep after inactivity.
- The first request after inactivity may take up to **30–60 seconds** due to cold start.
- The database is hosted on a free plan.
- For usage limit purposes, database data may be erased after some time.

If data is missing, please recreate a company and continue testing.

---

# 🔐 Authentication

There are two authentication mechanisms used in this system:

1️⃣ JWT (for Admin and Member users)  
Header:  
Authorization: Bearer <JWT_TOKEN>

2️⃣ API Key (for client applications checking feature flags)  
Header:  
X-API-KEY: <company_api_key>

---

# 🏢 Step 1 — Create a Company (Public)

POST `/companies`

Creates a new company and an Admin user.

### Request
```json
{
  "companyName": "jp tech inc",
  "admin": {
    "username": "john_doe",
    "email": "john.doe@example.com",
    "password": "securepassword123"
  }
}
```

Response
- companyId
- apiKey (used for client feature checks)
- admin information

---

🔑 Step 2 — Login  
POST `/auth/login`

Use admin credentials created in Step 1.

Request
```json
{
  "email": "john.doe@example.com",
  "password": "securepassword123"
}
```

Response  
Returns a JWT token (plain string).

Use this token for all secured endpoints.

---

🏢 Company APIs

Get Company Details (ADMIN only)  
GET `/companies`

Returns:
- companyId
- companyName
- apiKey
- admin info

Requires:  
JWT token (ADMIN role)

---

👥 User Management (ADMIN only)

Create User  
POST `/users`

Request
```json
{
  "username": "member_user",
  "email": "member@test.com",
  "password": "password123",
  "role": "MEMBER"
}
```

Roles:
- ADMIN
- MEMBER

Update User  
PATCH `/users/{userId}`

Allows updating:
- username
- email
- role

---

🚩 Feature Flag Management

Create Feature Flag (ADMIN only)  
POST `/feature-flags`

```json
{
  "featureFlagName": "new-dashboard",
  "enabled": true
}
```

Toggle Feature Flag (ADMIN only)  
PATCH `/feature-flags/{featureName}`

```json
{
  "enabled": false
}
```

Get All Feature Flags  
GET `/feature-flags`

Returns list of all feature flags for the company.

Get Specific Feature Flag  
GET `/feature-flags/{featureName}`

Returns detailed information about one feature flag.

Delete Feature Flag (ADMIN only)  
DELETE `/feature-flags/{featureName}`

Removes the feature flag.

---

🌐 Client Feature Check API

Check If Feature Is Enabled  
GET `/api/features/{featureName}`

Header:
X-API-KEY: <company_api_key>

Response:
- true
- or
- false

This endpoint is meant for external client applications to dynamically enable or disable features.
