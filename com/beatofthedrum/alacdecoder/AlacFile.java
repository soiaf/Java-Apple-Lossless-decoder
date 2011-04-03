/*
** AlacFile.java
**
** Copyright (c) 2011 Peter McQuillan
**
** All Rights Reserved.
**                       
** Distributed under the BSD Software License (see license.txt)  
**
*/
package com.beatofthedrum.alacdecoder;

class AlacFile
{

	int input_buffer[] = new int[81920];
	int ibIdx = 0;
	int input_buffer_bitaccumulator = 0; /* used so we can do arbitary
						bit reads */

	int samplesize = 0;
	int numchannels = 0;
	int bytespersample = 0;


	/* buffers */
	int predicterror_buffer_a[] = new int[65536];
	int predicterror_buffer_b[] = new int[65536];

	int outputsamples_buffer_a[] = new int[65536];
	int outputsamples_buffer_b[] = new int[65536];

	int uncompressed_bytes_buffer_a[] = new int[65536];
	int uncompressed_bytes_buffer_b[] = new int[65536];



	/* stuff from setinfo */
	int setinfo_max_samples_per_frame = 0; // 0x1000 = 4096
	/* max samples per frame? */
	
	int setinfo_7a = 0; // 0x00
	int setinfo_sample_size = 0; // 0x10
	int setinfo_rice_historymult = 0; // 0x28
	int setinfo_rice_initialhistory = 0; // 0x0a
	int setinfo_rice_kmodifier = 0; // 0x0e
	int setinfo_7f = 0; // 0x02
	int setinfo_80 = 0; // 0x00ff
	int setinfo_82 = 0; // 0x000020e7
	/* max sample size?? */
	int setinfo_86 = 0; // 0x00069fe4
	/* bit rate (avarge)?? */
	int setinfo_8a_rate = 0; // 0x0000ac44
	/* end setinfo stuff */
 
}