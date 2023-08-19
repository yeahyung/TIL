## Memory

### Paging
 - paging이란 process가 할당받은 메모리 공간을 일정한 `page` 단위로 나누어, 물리 메모리에서 `연속되지 않은` 서로 다른 위치에 저장하는 메모리 관리 기법

#### 논리적 주소
 - process가 memory에 적재되기 위한 독자적인 주소 공간인 논리적 주소가 생성됨
 - process마다 독립적으로 할당되며 0번지부터 시작

#### 물리적 주소
 - process가 실제로 메모리에 적재되는 위치

#### 주소 바인딩(address binding)
 - CPU가 기계어 명령을 수행하기 위해 process의 논리적 주소가 실제 물리적 메모리의 어느 위치에 매핑되는지 확인하는 과정(/w page table)
 - process 내에선 memory가 연속적으로 위치(논리적 메모리), 실제 물리적 메모리에서는 연속되지 않음

#### Paging의 메모리 단편화(Memory Fragmentation)
 - 물리적 메모리 공간이 작은 공간으로 나눠져서 메모리가 충분함에도 불구하고 할당히 불가능한 상태
 - 외부 단편화
   - 메모리 상에 비어있는 크기가 작아서, 빈 메모리 공간임에도 활용되지 못하는 문제
   - process의 논리적 주소 공간과 물리적 메모리가 같은 크기의 page 단위로 나누어지기 때문에 발생하지 않음
 - **내부 단편화**
   - process가 필요한 양보다 더 큰 메모리가 할당되서 메모리 공간이 낭비되는 상황
   
#### Segmentation
 - process가 할당받은 메모리 공간을 `논리적 의미 단위(segment)`로 나누어, 연속되지 않은 물리 메모리 공간에 할당될 수 있도록 하는 메모리 관리 기법(/w segment table)
 - `code`, `data`, `heap`, `stack` 등의 기능(의미) 단위로 나눈다(pageing 과 다르게 일정한 크기가 아니다!)
 - **외부 단편화**
   - 서로 다른 크기의 segment 들이 메모리에 적재되고 제거되는 일이 반복되면, 외부 단편화 문제가 발생할 수 있음
 - 내부 단편화
   - segment 크기만큼 메모리를 할당하므로 내부 단편화 문제가 발생하지 않음

### Paged Segmentation
 - segmentation을 기본으로 하되, 이를 다시 동일 크기의 page로 나어 물리 메모리에 할당하는 메모리 관리 기법
 - 프로그램을 의미 단위의 `segment`로 나누고 개별 `segment`의 크기를 `page`의 배수가 되도록 하는 방법
   - 이를 통해 segmentation에서 발생할 수 있는 외부 단편화 문제를 해결

### Virtual Memory
 - process 전체가 메모리에 올라오지 않더라도 실행이 가능하도록 하는 기법
 - 사용자 프로그램이 물리적 메모리보다 커져도 실행이 가능하다는 장점이 있다.
 - 운영체제는 가상 메모리 기법을 통해 프로그램의 논리적 주소 영역에서 `필요한 부분`만 물리적 메모리에 적재하고, 직접적으로 필요하지 않은 메모리 공간은 디스크(Swap 영역)에 저장

#### 요구 페이징(Demand paging)
 - 당장 사용될 주소 공간을 page 단위로 메모리에 적재하는 방법
 - 특정 page에 대해 cpu의 요청이 들어온 후에 해당 page를 메모리에 적재
   - 당장 실행에 필요한 page만을 메모리에 적재하기 때문에 메모리 사용량이 감소하고, 프로세스 전체를 메모리에 적재하는 입출력 오버헤드도 감소하는 장점이 있다.
 - `유효/무효 비트(valid/invalid bit)`를 page table에 두어 각 page가 메모리에 존재하는지 표시

#### Page Fault
 - CPU가 `무효 비트(invalid bit)`로 표시된 page에 엑세스하는 상황
 - CPU가 무효 page에 접근하면 주소 변환을 담당하는 MMU(Memory Management Unit)가 page fault trap을 발생시키게 되고 page fault 처리
   - CPU 페이지 N 참조 -> page table 에서 페이지 N 이 무효 상태임을 확인 -> MMU 에서 Page Fault trap 발생 -> 페이지 N 을 물리적 주소의 빈 프레임에 적재하고 page table 업데이트(유효)

##### Page 교체 알고리즘
 - 페이지 교체
   - Page Fault가 발생해서 요청된 페이지를 디스크에서 메모리로 가져올 때, 메모리에 공간이 부족한 경우 메모리에 올라와 있는 페이지를 디스크로 옮겨서 메모리 공간을 확보해야함
 - page fault가 최대한 적게 일어나는 것이 좋다.
 - `FIFO`, `LRU(Least Recently Used)`, `LFU(Least Frequently Used)`