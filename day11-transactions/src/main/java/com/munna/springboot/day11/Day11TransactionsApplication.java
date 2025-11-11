package com.munna.springboot.day11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day11TransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day11TransactionsApplication.class, args);
	}

}


/*

1. What is a Transaction?
-------------------------

A transaction ensures a series of database operations execute as a single unit of work — 
either all succeed or all fail.

ACID Properties:
	Atomicity – all or nothing
	Consistency – DB stays valid
	Isolation – concurrent transactions independent
	Durability – once committed, data persists
	

2. How @Transactional Works
---------------------------

When a method is annotated with @Transactional, Spring:

	1.Creates a proxy for that method.
	2.Starts a transaction before execution.
	3.Commits if successful.
	3.Rolls back if an unchecked exception (RuntimeException or Error) occurs.
	
	
3. Rollback Rules
-----------------

| Exception Type                                      | Default Rollback? | How to Force                                        |
| --------------------------------------------------- | ----------------- | --------------------------------------------------- |
| **RuntimeException / Error**                        |  Yes              | Default                                             |
| **Checked Exception (e.g. IOException, Exception)** |  No               | Use `@Transactional(rollbackFor = Exception.class)` |


4. Propagation (Optional for now)
---------------------------------

Defines how existing transactions behave when new transactional methods are called.

Common modes:

	REQUIRED (default) – join existing or create new.
	REQUIRES_NEW – suspend current and start new.
	MANDATORY – must exist, else exception.


5. Best Practices
-----------------

	Keep transactions at the service layer, not controllers.
	Keep methods short and cohesive.
	Avoid calling transactional methods from within the same class (proxy won’t apply).
	Always log rollback causes.
	Use readOnly=true for read operations to optimize performance.
	
	
Day 11 — Summary
----------------

| Concept          | Description                                            |
| ---------------- | ------------------------------------------------------ |
| `@Transactional` | Wraps multiple DB actions in one atomic operation      |
| Rollback         | Auto for runtime exceptions; manual for checked        |
| Propagation      | How transactions nest or join                          |
| RollbackFor      | Specifies which exception triggers rollback            |
| readOnly         | Optimizes for SELECT-only methods                      |
| Real-time Use    | Used in service layer for CRUD, payments, orders, etc. |



*/