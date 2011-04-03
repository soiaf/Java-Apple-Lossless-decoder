/*
** StreamUtils.java
**
** Copyright (c) 2011 Peter McQuillan
**
** All Rights Reserved.
**                       
** Distributed under the BSD Software License (see license.txt)  
**
*/

package com.beatofthedrum.alacdecoder;

class StreamUtils
{
	public static void stream_read(MyStream mystream, int size, int[] buf, int startPos)
	{
		int bytes_read = 0;
		byte[] bytebuf = new byte[size];
		
		try
		{
			bytes_read = mystream.stream.read(bytebuf, 0, size);
		}
		catch (Exception err)
		{
			System.err.println("stream_read: exception thrown: " + err);
		}
		mystream.currentPos = mystream.currentPos + bytes_read;
		
		for(int i=0; i < bytes_read; i++)
		{
			buf[startPos + i] = bytebuf[i];
		}

	}

	public static int stream_read_uint32(MyStream mystream)
	{
		int v = 0;
		int tmp = 0;
		byte[] bytebuf = new byte[4];
		int bytes_read = 0;

		try
		{
			bytes_read = mystream.stream.read(bytebuf, 0, 4);
			mystream.currentPos = mystream.currentPos + bytes_read;
			tmp =  (bytebuf[0] & 0xff);

			v = tmp << 24;
			tmp =  (bytebuf[1] & 0xff);

			v = v | (tmp << 16);
			tmp =  (bytebuf[2] & 0xff);

			v = v | (tmp << 8);

			tmp =  (bytebuf[3] & 0xff);
			v = v | tmp;

		}
		catch (Exception err)
		{
			System.err.println("stream_read_uint32: exception thrown: " + err);
		}
		
		return v;
	}

	public static int stream_read_int16(MyStream mystream)
	{
		int v = 0;
		try
		{
			v = mystream.stream.readShort();
			mystream.currentPos = mystream.currentPos + 2;
		}
		catch (Exception err)
		{
		}
		
		return v;
	}
	public static int stream_read_uint16(MyStream mystream)
	{
		int v = 0;
		int tmp = 0;
		byte[] bytebuf = new byte[2];
		int bytes_read = 0;

		try
		{
			bytes_read = mystream.stream.read(bytebuf, 0, 2);
			mystream.currentPos = mystream.currentPos + bytes_read;
			tmp =  (bytebuf[0] & 0xff);
			v = tmp << 8;
			tmp =  (bytebuf[1] & 0xff);
			
			v = v | tmp;
		}
		catch (Exception e)
		{
		}

		return v;
	}

	public static int stream_read_uint8(MyStream mystream)
	{
		int v = 0;
		int bytes_read = 0;
		byte[] bytebuf = new byte[1];
		
		try
		{
			bytes_read = mystream.stream.read(bytebuf, 0, 1);
			v =  (bytebuf[0] & 0xff);
			mystream.currentPos = mystream.currentPos + 1;
		}
		catch (Exception e)
		{
		}		
			
		return v;
	}

	public static void stream_skip(MyStream mystream, int skip)
	{
		byte[] bytebuf = new byte[8192];
		int toskip = skip;
		int toget = 0;
		int bytes_read = 0;
		int method_to_skip = 2;		// method used to skip data

		if(toskip < 0)
		{
			System.err.println("stream_skip: request to seek backwards in stream - not supported, sorry");
			return;
		}

		/*
		** 3 ways to skip within file
		** 1) read in chunks (ignoring data) till skip done
		** 2) use skipBytes to skip required amount
		** 3) with a stream that supports seeking, seek to new location
		*/
	
		if(method_to_skip == 1)
		{		
			while(toskip > 0)
			{
				if(toskip > 8192)
				{
					toget = 8192;
					toskip = toskip - 8192;
				}
				else
				{
					toget = toskip;
					toskip = 0;
				}

				try
				{
					bytes_read = mystream.stream.read(bytebuf, 0, toget);
					mystream.currentPos = mystream.currentPos + bytes_read;
				}
				catch (Exception e)
				{
				}
			}
		}
		else if (method_to_skip == 2)
		{
			try
			{
				bytes_read = mystream.stream.skipBytes(toskip);
				mystream.currentPos = mystream.currentPos + bytes_read;
			}
			catch (java.io.IOException ioe)
			{
			}
		}
	}

	public static int stream_eof(MyStream mystream)
	{
		// TODO

		return 0;
	}

	public static int stream_tell(MyStream mystream)
	{
		return (mystream.currentPos);
	}
	public static int stream_setpos(MyStream mystream, int pos)
	{		
		return -1;
	}

}

