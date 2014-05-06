MIPS
====

CMSE611 Advanced Computer Architecture

Objective -
To experience the design issues of advanced computer architectures through the design of an analyzer for a 
simplified MIPS CPU using high level programming languages. The considered MIPS CPU  adopts a multicycle  pipeline  processor  to  dynamically  schedule  instruction  execution  and  employs  caches  in  order  to expedite memory access.

Project Statement -
Consider a simplified version of the MIPS instruction set architecture shown below in Table 1 and whose formats are provided at the end of this document.

- Reduced MIPS instruction set
1. Data Transfers - LW, SW, L.D, S.D
2. Arithmetic/ logical - DADD, DADDI, DSUB, DSUBI, AND, ANDI, OR, ORI, ADD.D, MUL.D, DIV.D, SUB.D
3. Control - J, BEQ, BNE 
4. Special purpose - HLT (to stop fetching new instructions)

You  need  to  develop  an  architecture simulator for the MIPS computer  whose organization  is  shown  in  Figure  1.  
The simulator  is  to  accept  a  program  as  an input  in  the  MIPS  assembly  using  the subset  of  instructions  in table above. The output  of  simulator  will  be  a  file containing the cycle time  at  which  each instruction  completes the various  stages, and  statistics  for  cache  access.  The detailed  specifications  of  the  input  and output files will be provided later in this document.  The  following  explains  the CPU and Memory system:

Memory:  The  MIPS  machine  has  an instruction cache (I-Cache)  organized  as direct-mapped with 4 blocks and the block size is 4 words. In addition, the  machine  has a data cache (DCache). D-Cache is a 2-way set associative with a total of four 4-words blocks. A least recently used block replacement strategy is to be applied for D-Cache. A write-back strategy is  employed  with  a  write-allocate policy.  I-Cache is used in the instruction fetch stage while D-Cache is accessed in the memory stage. Both I-Cache and D-Cache are connected  to main memory using a shared bus. In the case of a cache miss, if main memory is busy serving the other cache, we have to wait for it to be free and then start accessing it. In other words, latency of the main memory will be dynamic depending on the time  of a request and the state of previous requests. If both caches experience a miss at the same  cycle, the I-Cache will be given priority. The  main  memory  is  accessible  through  one-word-wide  bus.  The  access  time  for  memory,  I-Cache  (hit time) and D-Cache (hit time) are specified as input in a configuration file, named “config.txt”. Memory is 2-way interleaved making the access time for 2 consecutive words equals T+k cycles, where T and k are the access  time  of  memory  and  cache,  respectively.   All  access  to  memory  will  be  word-aligned.  Thus,  the cache miss penalty will be 2(T+k).


CPU:  The  MIPS  computer  employs  a  multi-stage  pipeline  processor  in  order  to  dynamically  schedule instruction execution.  There  are  generally  four  stages  in  the  pipeline,  namely,  Instruction  fetch  (IF), instruction decoding  and  operand  reading  (ID),  Execution  (EX),  and  Write  back  (WB).  The  EX  stage include both the ALU and data memory (MEM) access stages.  There are  four  distinct ALU units for:  one Integer arithmetic,  one  FP add/subtract, one  FP multiplication, and  one  FP division. The integer unit  (IU) always takes one cycle for the execution. The specification of whether the individual FP units are pipelined or not, as well as the latency of each unit are to be provided in an  input file  with the name “config.txt”.  An example  configuration  is  shown  in  figure below, where both  the  FP  adder  and  multiplier  are pipelined,  tacking  4  and  6  cycles,  respectively. The  FP  division  is not  pipelined  and  needs  20 cycles  to  complete.  Note  that  the  number  of cycles  for  the  instruction  fetch (IF)  and  data access  (MEM)  stages  depends  on  the  cache performance, e.g. miss or hit. For load and store nstructions,  the  address  is  calculated  by  IU before  the  data  cache  is  accessed.  Meanwhile for  the  integer  instructions,  e.g.,  DADD,  the instruction will spend only 1 cycle MEM stage, before advancing WB stage. 

  The pipelined processor enables instruction-level parallelism with in-order issue, out-of-order execution and out-of-order completion. All instructions  should  pass through the  IF  stage in order. If an instruction cannot leave the ID stage because a functional unit is busy, a subsequent instruction will not be  fetched. However, if instructions use different functional units after they pass the ID stage, they can get stalled or bypass each other in the EX stage and thus leave the EX stage out of order.  Unconditional jumps complete in ID  stage. The fetched instruction  (the next instruction in the program)  will be flushed from IF  stage in that case. In other words a “J” instruction will waste one cycle if the following instruction in the program is in cache, or waste  as  many  cycles  as  the  cache  miss  penalty  if  the  next instruction  was  not  in  cache.  Conditional branches are  also  resolved in the “ID” stage  as well.  Meanwhile the CPU will go ahead and fetch the next instruction, in other words, always “not-taken prediction” will be used in the IF stage. If the branch turns to be  “non-taken”  the pipeline will not be stalled.  However,  if  the branch is taken, the control unit  will flush the  fetched  instruction  and update the program counter. In other words, if the branch is “Taken”,  one cycle will be wasted  (unless a cache miss is encountered while the branch is being evaluated).  The branching instructions  do  not  stall  because  of  structural  hazard  related  to  the  integer  unit.  On  the  other  hand,  a conditional branching instruction may suffer RAW hazard, in which case the instruction will be stalled in the ID stage until the RAW hazard is resolved. The table below shows the number of cycles each instruction takes in the EX stage.


- Instructions - Number of Cycles in “Execute” Stage
1. HLT, J - 0 Cycles (finish in ID stage)
2. BEQ, BNE - 0 Cycle (finish in ID stage)
3. DADD, DADDI, DSUB, DSUBI, AND, ANDI, OR, ORI - 2 Cycles (one for IU + one for MEM stage)
4. LW, SW, L.D, S.D - 1 Cycle + memory access time (D-Cache)
5. ADD.D, SUB.D - Specified in the “config.txt” file
6. MUL.D - Specified in the “config.txt” file
7. DIV.D - Specified in the “config.txt” file

As pointed out, the configuration of the FP add, multiply and division units  is to be  specified in one of the input  files  with  the  name  as  config.txt.  The  simulator  accepts  three  additional  input  files;  one  is  for  the program containing a  mix of the assembly language  instructions in  Table 1,  a file  for the initial contents of data  memory  and  another  file  for  the  values  stored  in  the  integer  registers.  The  output  of  the  simulator
should contain the following information:

(i). The execution time a program takes (by reporting the cycle  number that each instruction completes each stage it passes through).

(ii).  The  structural  and  data  hazards  (RAW,  WAR  and  WAW)  in  the  assembly  code  which  result  in pipeline stalls.

(iii).  The  performance  of  the  instruction  and  data  caches.  The  evaluation  criteria  should  be  the  total number of cache access requests and the number of the cache hits for the particular cache.

- Addition considerations  

1)  The pipeline processor does not have forwarding hardware.

2)  In addition to the 32 word-size registers (for integers), there are 32 FP registers; each has 64 bits.

3)  Floating point calculations will have no impact on the required output of your simulator.  In fact, only the contents of the integer registers need to be read from the input file, and  you do not even need to allocate storage in your simulator for floating point registers.


4)  The number of cycles required by the ALU depends on the latency of the involved functional unit and whether it is pipelined or not. 

5)  Instructions and data are stored in memory starting at address 0x0 and 0x100 respectively. Load and store instructions use word-aligned addresses when accessing data.

6)  Both  conditional  and  unconditional  jump  instructions  can  be  forward  and  backward.  You  can assume that a program will not create a closed loop.

7)  Integer  and  floating  point  operations  use  the  same  write  port  and  hence  structural  hazards  can occur. Structural hazards are detected before entering the WB stages. The functional unit that has the instruction will be stalled (stay busy) if the instruction cannot pro ceed to the WB stage.  In case multiple instructions are ready at the same time to the WB stage, the priority will be given to the functional unit that in not pipelined and takes the most execution cycle (based on the parameters in “config.txt”). If there is a tie, the instruction that was issued the earliest will have the priority.

8)  An instruction stalled for RAW hazard in the ID stage can get the values in the same cycle WB takes place.

9)  WAW hazards are detected at the ID stages and resolved by stalling the pipeline.

10) All caches are blocking and do not support “hit under misses”.

11) The HLT instruction will mark the end of the program, i.e., fetching will  seize  as soon as the HLT instruction is decoded. In your implementation you can assume that the program will have two HLT instructions at the end  in order to stop accessing  the cache once the first HLT reaches the decode stage. You can ignore the second HLT instruction.

- Sample Files - 

inst.txt
GG: L.D F1, 4(R4)

L.D F2, 8(R5)

ADD.D    F4, F6, F2

SUB.D    F5, F7, F1

MUL.D    F6,  F1, F5

ADD.D    F7, F2, F6

ADD.D    F6, F1, F7

DADDI R4, R4,  4

DADDI R5, R5,  4

DSUB    R1, R1, R2

BNE    R1, R3, GG 

HLT

HLT

config.txt
FP adder:  4, yes

FP Multiplier:  6, yes

FP divider: 20, no

Main memory: 2

I-Cache: 1

D-Cache: 1

data.txt
00000000000000000000000000000010

00000000000000000010000101010101

00000000001001110101010100010100

00000101010111110001010101010101

00000000101010101010101010101111

00000100010010010101110000001010

00000000000000001111111111111111

00000000000000001111111111111111

00000000000000001111100010100110

00000000000000001010101101110110

00000000000101011010011110101011

00000000000100000000000000000111

00000000000000101110000000010101

00000000000000000010101110101101

00000000000000000000000000000000

00000000000001101101010011110101

00000000000000000000000001100001

00000000000000110000101010101001

00000000000000000000010101010101

00000000000000001111111111111000

00000000000000000000000000000011

00000000000000000000000000000001

00000000000000001111100110100110

00000000000000000001010101010101

00000000000001101101010011110101

00000000000001000010101110010101

00000000000001101101111001010101

00000000000000000101010101110100

00000000000001001010101011110101

00000000000000000000000000010101

00000000000111101110000000000000

00000000000001101101010011110100

reg.txt
00000000001000000110011000000011

00000000000000000000000000000011

00000000000000000000000000000001

00000000000000000000000000000001

00000000000000000000000100000000

00000000000000000000000100000000

00000000000000111011111111010101

00000000000000000001111100100111

00000000000000101010101110101011

00000000000000000101010000000010

00000000000101011111101010101010

00000101011011110101000000000101

01101010101010101011010010110101

00000010101011110001101010101010

00000110101011000010101010101010

00000010101111011010101010011111

00000000000001010101010101101011

00000000000000000000111010101110

00000000000000000000000010101001

00000000000000001110101010101010

01010101011111010101010101011011

00000000000101010101101110101011

00000000111010101010101010101011

00000000000001110101010101010101

00000011010101010100000001010101

00000000000000001010101010010011

00110010010010110101010101001000

01101010101010101011101010101011

00000000101010101010111111101111

00000000000000000000010110101010

00000000010101010101010101010111

00000000000000000000000000000000
