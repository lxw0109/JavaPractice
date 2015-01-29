#!/usr/bin/python
#File: statistics.py
#Author: lxw #Time: 2014-03-18
#Usage:	To get the information like following: '#of' means 'number of'
# user_id1	brand_id1	#of0	#of1	#of2	#of3
# user_id1	brand_id2	#of0	#of1	#of2	#of3
# user_id1	brand_id3	#of0	#of1	#of2	#of3
# ...
# user_id2	brand_id1	#of0	#of1	#of2	#of3
# user_id2	brand_id2	#of0	#of1	#of2	#of3
# user_id2	brand_id4	#of0	#of1	#of2	#of3
import pdb

dic = {}
filename = raw_input('Which file do you want to deal with: 1.t_alibaba_data.csv\t2.test\n')
if filename == '1':
	filename = 't_alibaba_data.csv'
else:
	filename = 'test'

#with open('./t_alibaba_data.csv') as f:
with open(filename) as f:
	f.readline()	# omit the first line
	for line in f:
		# print line,
		# Only interested in int(cols[0])(user_id), int(cols[1])(brand_id), cols[2](type)
		cols = line.split(',')
		# If there is not int(cols[0])(the current userId), then add as '{}'. If there is int(cols[0]), nothing happens.
		dic.setdefault(int(cols[0]), {})		# Can not be 'dic[int(cols[0])] = {}'
		dic[int(cols[0])].setdefault(int(cols[1]), {})	# Convert int(cols[1])(brand_id) into INT for sorting.

		#'if not dic[int(cols[0])][int(cols[1])].get(cols[2]):' NOT OK. WHAT IF dic[int(cols[0])][int(cols[1])][cols[2]] == 0?
		if not dic[int(cols[0])][int(cols[1])]:	# New brand_id
			# dic[int(cols[0])][int(cols[1])] == {}
			dic[int(cols[0])][int(cols[1])] = {'0' : 0, '1' : 0, '2' : 0, '3':0}
			dic[int(cols[0])][int(cols[1])][cols[2]] = 1
		else:	# Already exist.
			dic[int(cols[0])][int(cols[1])][cols[2]] += 1

# Convert dic into list
dicList = list(dic)
dicList.sort()

with open('./result', 'w') as f1:
	for userId in dicList:
		f1.write(str(userId) + ':\n')
		brandList = list(dic[userId])	# Convert 'dict of dict' into 'list of dict' for sorting.
		brandList.sort()
		for brandId in brandList:
			f1.write(str(brandId)+ '\t')
			f1.write(str(dic[userId][brandId]['0']) + '\t')
			f1.write(str(dic[userId][brandId]['1']) + '\t')
			f1.write(str(dic[userId][brandId]['2']) + '\t')
			f1.write(str(dic[userId][brandId]['3']) + '\n')
		#f1.write('-------------------------' + str(userId) + ' IS OVER--------------------------------\n\n')
		f1.write('\n')

# delete dic
del dic
