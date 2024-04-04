import { createReducer, on } from '@ngrx/store';
import { increment, decrement, reset } from './app.actions';

export const mockQuestions = [
  {
    "id": 1,
    "user": {
      "id": 1,
      "name": "Test user 1",
      "email": "dsdsadsadd",
      "password": "$2a$10$eO74b2tPFuNeApDDWnls0uzOAMC8glzq.J.NOAPfCb4tx1JFMz0g6",
      "bio": "Hello! I'm a user",
      "score": 0,
      "role": "USER",
      "accountStatus": "ACTIVE"
    },
    "title": "Q1 Test",
    "text": "Uh, this is my first question, hope it's good.",
    "createdAt": "2024-04-04T11:11:11.792557Z",
    "image": null,
    "score": 0
  }
];

export const appReducer = createReducer(
  mockQuestions
);
