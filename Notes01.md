# 🌱 Spring Boot – Big Picture Overview

## What Spring Boot actually is

Spring Boot is not a replacement for Spring.

It is a productivity layer on top of Spring that removes:
- XML configuration
- Boilerplate setup
- Manual dependency wiring
- Server configuration pain

👉 Goal: Build production-ready Spring applications in minutes.

---

## 🔥 Problems Spring Boot Solves (Why It Exists)

| Traditional Spring         | Spring Boot          |
| -------------------------- | -------------------- |
| XML config                 | Java + annotations   |
| Manual dependency versions | Starter-managed      |
| External server setup      | Embedded server      |
| Complex setup              | Opinionated defaults |
| Slow start                 | Fast bootstrap       |

---

## ⚙️ Core Pillars of Spring Boot
Spring Boot stands on 3 pillars:
1. Auto-Configuration
2. Starters
3. Embedded Server

---

## 🧠 Auto-Configuration (MOST IMPORTANT)

What is Auto-Configuration?
> Spring Boot automatically configures beans based on:

- Dependencies present
- Classpath
- Existing configuration
- Conditions

📌 You don’t write config, Boot infers config.


Example: Spring Web

spring-boot-starter-web

Spring Boot automatically:
- Creates DispatcherServlet
- Configures Tomcat
- Registers RequestMappingHandlerMapping
- Enables JSON (Jackson)
- Configures HTTP message converters

👉 You didn’t write any of this.

---

## How Auto-Configuration Works (Internally)

1. @SpringBootApplication
2. ⬇
3. @EnableAutoConfiguration
4. ⬇
5. Reads spring.factories / AutoConfiguration.imports
6. ⬇
7. Applies conditional configs

---

## Conditional Annotations (KEY INTERVIEW AREA)

| Annotation                     | Meaning             |
| ------------------------------ | ------------------- |
| `@ConditionalOnClass`          | If class exists     |
| `@ConditionalOnMissingBean`    | If bean NOT defined |
| `@ConditionalOnProperty`       | If property exists  |
| `@ConditionalOnWebApplication` | If web app          |

📌 Golden Rule
> Spring Boot configures ONLY if you haven’t.

Real Example

@ConditionalOnClass(DataSource.class)

@ConditionalOnMissingBean(DataSource.class)

public class DataSourceAutoConfiguration { }

➡ If DataSource dependency exists

➡ AND you didn’t define your own

➡ Spring Boot creates one

---

## Disable Auto-Configuration (Advanced)

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)

---

## 📦 Spring Boot Starters
What is a Starter?
> A starter is a curated dependency bundle for a use-case.

📌 Not a feature, just dependency management.

---

## Why Starters Exist

Without starters:

spring-webmvc

jackson

tomcat

validation

logging


With starter:

spring-boot-starter-web

👉 One dependency → many resolved automatically

---

## Common Starters (Must Know)

| Starter                          | Purpose         |
| -------------------------------- | --------------- |
| `spring-boot-starter-web`        | REST / MVC      |
| `spring-boot-starter-data-jpa`   | JPA + Hibernate |
| `spring-boot-starter-security`   | Security        |
| `spring-boot-starter-test`       | Testing         |
| `spring-boot-starter-actuator`   | Monitoring      |
| `spring-boot-starter-validation` | Bean validation |


---

## Starter Naming Rule

spring-boot-starter-<feature>

📌 Predictable = easy learning

---

🧠 Starters + Auto-Config (Together)

| Starter Adds | Auto-Config Does |
| ------------ | ---------------- |
| Dependencies | Bean creation    |
| Libraries    | Wiring           |
| Versions     | Defaults         |

👉 Starters bring tools

👉 Auto-config uses tools

---

## 🚨 Interview Traps & Smart Answers
❓ Is Spring Boot magic?

NO.

It uses:
- Classpath scanning
- Conditional annotations
- Spring core features

---

❓ Can we override auto-configuration?

✅ Yes:
- Define your own bean
- Use application.properties
- Exclude auto-config

---

❓ Difference: Spring vs Spring Boot?
> Spring = framework
> Spring Boot = opinionated setup + productivity

---

❓ Does Spring Boot remove Spring?

❌ No.

Spring Boot depends on Spring Core.

---

## 🧪 Simple Demo (Mental Model)

@SpringBootApplication

public class App {

    public static void main(String[] args) {
    
        SpringApplication.run(App.class, args);
        
    }
    
}

Behind this one line:
- Context created
- Beans scanned
- Auto-config applied
- Server started
- App ready









