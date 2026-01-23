# Git Conventions

To ensure our history is readable, automated tools work correctly, and our workflow remains smooth, we follow strict conventions for commits, branches and pull requests.

<br>

### Allowed Commit Types

| Type | Description |
| :--- | :--- |
| `feat` | A new feature for the user. |
| `fix` | A bug fix. |
| `docs` | Documentation only changes. |
| `style` | Formatting, missing semi-colons, white-space; no code change. |
| `refactor` | Refactoring production code (no new features or bug fixes). |
| `test` | Adding missing tests or refactoring existing tests. |
| `chore` | Updating build tasks, package manager configs, etc. |
| `ci` | Changes to CI configuration files and scripts. |

<br>

## 1. Branches & Pull Requests

### Naming

We use the following structure for branch names to ensure context and traceability:

`<type>/decide-<issue-number>`

* **Type:** Must match one of the allowed commit types.
* **Issue ID:** The specific issue number from our issue tracker.

**Examples:**
* `feat/decide-1`
* `fix/decide-2`
* `docs/decide-3`

### Merge strategy
The repo is set up to only allow **"squash merges"**. Therefore, make sure the "Pull Request title" follows the convention:

```<type>: <subject>. #<issue-number>```

### Other notes
- Feature and fix branches **should be deleted** after merge.
- Branch type reflects the primary intent, commit types inside the branch can vary.

<br>

## 2. Commit Message Structure

### 2.1. Merge Commits
Since we are using "squash merging", the following convention applies to the **"final merge commit"**:

```text
<type>: <subject>

<optional body>

Closes #<issue-number>
```
**Subject Line**: Use the imperative tense (e.g., "add" not "added" or "adding").

**Body (Optional)**: Use if the change requires explanation. Focus on **what** and **why**, not *how*.

### Example
```text
fix: handle null token in session storage

The session was crashing when local storage was cleared manually. Added a check to regenerate token if null.

Closes #17
```



### 2.2. Commits inside a branch
The individual commits inside a branch will automatically show up like this in the "squashed commit" (if not manually overwritten):
```text
<Pull Request Title>

- Add login endpoint
- Add password hashing
- Fix typo in auth middleware
```
So, for commits _inside_ a branch, the only guideline is that we make sure they are clean and readable (also uses the imperative tense).

### Example:

```text
Add nordic languages in system menu
```
