import random
import heapq 
import itertools
import csv
from csv import reader
from csv import DictReader

################################################
#Create a random input file with (website, tag)
################################################
fTags = open("tags.txt", "r")
tags = []
for line in fTags:
	tags.append(line.split('\r')[0])
fTags.close()


websites = []
with open('10megaUrl.csv', 'r') as csvfile:
    # iterate over each line as a ordered dictionary and print only few column by column name
	csv_dict_reader = DictReader(csvfile)
	i = 0
	for row in csv_dict_reader:
		if i == 100:
			break
		i = i + 1
		websites.append(row['Domain'])
csvfile.close()	

tagsLen = len(tags)
inputLines = []
for w in websites:
	x = random.sample(range(0, tagsLen - 1), 10)
	for i in x:
		newLine = w + " " + tags[i]
		inputLines.append(newLine)

random.shuffle(inputLines)

with open('input.tsv', 'wt') as inputF:
	tsv_writer = csv.writer(inputF, delimiter='\t')
	tsv_writer.writerow(['URL', 'TAG'])
	for item in inputLines:
		words = item.split(" ")	
		tsv_writer.writerow([words[0], words[1]])
inputF.close()

print("Finished creating url-tag file")
################################################
# hash all websites by tags (tagname, [w1,w2,w3])
################################################

Hash = {}
for line in inputLines:#each line contains - url tag
	words = line.split()
	Hash.setdefault(words[1],[]).append(words[0])

print("Finishd creating Hash for (tag, [w1 w2 w3 w4 ....])")
################################################
#Creat websites paires for each tag and count it (w1_w2, counter)
################################################
HashRes = {}
for k, v in Hash.iteritems():
	for pair in itertools.product(v, repeat=2):
		if pair[0] == pair[1]:
			continue
		p1 = pair[0] + " " + pair[1]
		if (p1 in HashRes):
			HashRes[p1] += 1
		else:
			HashRes[p1] = 1

print("Finishd creating websites paires for each tag and count it (w1_w2, counter)")
################################################
# for each w we aggregate all tuples (w1, {w1_w2, counter1), ... (w1_w3, counter)})	
################################################
MinHeapHash = {}
for kww, counter in HashRes.iteritems():
	kwwArr = kww.split()
	MinHeapHash.setdefault(kwwArr[0], []).append((kww, counter))

	wwk = kwwArr[1] + " " + kwwArr[0]	
	MinHeapHash.setdefault(kwwArr[1], []).append((wwk, counter))

print("Finishd creating : for each w we aggregated all tuples (w1, {w1_w2, counter1), ... (w1_w3, counter)})")
################################################
#Take only the top 10 results of each key and print to file
################################################

print("Take only the top 10 results of each key and print to file")
res = open("output.txt", "w")
for w, v in MinHeapHash.iteritems():
	v.sort(reverse=True, key=lambda x:x[1])
	i = 0
	for obj in v:
		if i == 10:
			break
		i = i + 1
		print >> res, obj
res.close()










