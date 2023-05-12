#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#define BUFFER_SIZE 1024

// 메세지 형태 정의
typedef struct {
  long msgtype;
  int value;
  char buf[BUFFER_SIZE];
} msgbuf;

int main() {
  int key_id;
  msgbuf mybuf;
  long msgtype = 1; // 수신받을 메세지 타입 미리 설정

  // 메세지 생성
  key_id = msgget((key_t) 1234, IPC_CREAT|0666);
  if (key_id == -1) {
    perror("msgget() error");
    exit(0);
  }

  while (1) {
    // 메세지 타입이 1 일 때 수신
    if (msgrcv(key_id, &mybuf, sizeof(msgbuf), 1, 0) == -1) {
      perror("msgrcv() error");
      exit(0);
    }

    // 수신 받은 메세지에서 value 출력
    printf("value : %d\n", mybuf.value);
  }
}