# Changelog - Wade Liffick

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

## 2025.04.18

### Added

- Designed test suite for the SecureMessage component
- Designed two different use cases for the SecureMessage component

### Updated

- Cleaned up the interface to make everything consistent and easier to use.
- Fixed up the logic in encrypt, decrypt, and isEncrypted so they behave correctly.
- Went through and made sure all the comments and method headers were written correctly.

## 2025.04.08

### Added

- Designed kernel implementation for SecureMessage component
- Created the 'MessageKey' class to represent unique (message, key) pairs
- Stored the messages, encrypted messages, and the key in a 'Map<MessageKey, String>'

### Updated

- Changed design to include multiple encrypted versions of the same message using different keys
- I removed 'setMessage', 'setKey', 'getMessage', and 'getKey' to reflect my new design.
- I made changes to 'equals()' and removed 'toString()'

## 2025.03.26

### Added

- Designed abstract class for SecureMessage component

### Updated

- I kept my design relatively the same but had to change a couple small things on my secondary methods to fit the overall concept I was going for.

## 2025.03.04

### Added

- Designed kernel and enhanced interfaces for SecureMessage component

### Updated

- I added the following Kernel methods to my implementation: setMessage, setKey, getMessage, and getKey. This was to add more functionality and practicality to my program. This will allow for a more tangible design to sending and receiving messages through encryption.

## 2025.02.20

- I touched up my component brainstorming document to fit a more practical use based on the guidance of Jeremy's notes.
- I created a new file called SecureMessage.java in my src folder.
- In this file, I implemented some of my methods.
- I tested and made sure that it is functional.
- I will continue to implement more functionality to this in the coming weeks.

## 2025.02.03

- I cloned the repo and ensured that all the necessary installations were complete in VSCode.
- I added my three brainstorming topics to the `01-component-brainstorming` document.
- I created this `CHANGELOG` document.

