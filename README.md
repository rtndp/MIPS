MIPS
====

CMSE611 Advanced Computer Architecture

Objective - To experience the design issues of advanced computer architectures through the design of an analyzer for a 
simplified MIPS CPU using high level programming languages. The considered MIPS CPU  adopts a multicycle  pipeline  processor  to  dynamically  schedule  instruction  execution  and  employs  caches  in  order  to 
expedite memory access.
Project Statement
Consider a simplified version of the MIPS instruction set architecture shown below in Table 1 and whose 
formats are provided at the end of this document.

Reduced MIPS instruction set

1. Data Transfers - LW, SW, L.D, S.D
2. Arithmetic/ logical - DADD, DADDI, DSUB, DSUBI, AND, ANDI, OR, ORI, ADD.D, MUL.D, DIV.D, SUB.D
3. Control - J, BEQ, BNE 
4. Special purpose - HLT (to stop fetching new instructions)

You  need  to  develop  an  architecture simulator for the MIPS computer  whose organization  is  shown  in  Figure  1.  
